package com.example.tmall.service;

import com.example.tmall.dao.*;
import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import com.example.tmall.exception.*;
import com.example.tmall.util.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class MemberService {
  @Autowired
  private MemberDao memberDao;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MailUtil mailUtil;

  public boolean checkUsername(String username) {
    return !memberDao.existsByUsername(username);
  }

  public void join(MemberDto.CreateRequest dto) {
    boolean useDefaultProfile = false;
    String uploadProfileName = dto.getProfile();
    String 프로필_이름 = "";
    File source = null;

    // 기본 프사를 사용하는 상황인지 체크
    if(uploadProfileName==null || uploadProfileName.isEmpty()) {
      useDefaultProfile = true;
    } else {
      source = new File(ImageUtil.TEMP_FOLDER, uploadProfileName);
      if (source.exists() == false) {
        useDefaultProfile = true;
      }
    }

    // 프로필을 복사하기 위해 원본과 복사본 파일을 준바
    if(useDefaultProfile) {
      프로필_이름 = ImageUtil.DEFAULT_PROFILE;
      source = new File(ImageUtil.PROFILE_FOLDER, 프로필_이름);
    }
    프로필_이름 = dto.getUsername() + FilenameUtils.getExtension(uploadProfileName);
    File dest = new File(ImageUtil.PROFILE_FOLDER, 프로필_이름);

    // 파일을 이동 후 원본을 삭제
    try {
      FileCopyUtils.copy(source, dest);
      if(!useDefaultProfile) {
        source.delete();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    memberDao.insert(dto.toEntity(encodedPassword, 프로필_이름, MemberLevel.BRONZE));
  }

  public void changeProfile(String uploadProfileName, String username) {
    // 현재 프사를 삭제 + 사용자 정보가 없을 경우 예외 발생
    Member member = memberDao.findByUsername(username).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
    File currentProfile = new File(ImageUtil.PROFILE_FOLDER, member.getProfile());
    if(currentProfile.exists())
      currentProfile.delete();

    // 새로 저장할 프사의 이름을 계산
    File origin = new File(ImageUtil.TEMP_FOLDER, uploadProfileName);
    String newProfileName = username + FilenameUtils.getExtension(uploadProfileName);
    File dest = new File(ImageUtil.PROFILE_FOLDER, newProfileName);

    // 파일 이동
    try {
      Files.move(origin.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    memberDao.update(Member.builder().username(username).profile(newProfileName).build());
  }

  public String findUsername(String email) {
    return memberDao.findUsernameByEmail(email).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
  }

  public void resetPassword(MemberDto.ResetPasswordRequest dto) {
    Member member = memberDao.findByUsername(dto.getUsername()).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
    String newPassword = RandomStringUtils.secure().nextAlphanumeric(10);
    String newEncodedPassword = passwordEncoder.encode(newPassword);
    memberDao.update(Member.builder().username(dto.getUsername()).password(newEncodedPassword).build());
    String html = "<p>아래 임시비밀번호로 로그인하세요</p>";
    html+= "<p>" + newPassword  + "</p>";
    mailUtil.sendMail("admin@icia.com", member.getEmail(), "임시비밀번호", html);
  }

  public boolean verifyPassword(String password, String username) {
    Member member = memberDao.findByUsername(username).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
    return passwordEncoder.matches(password, member.getPassword());
  }

  public MemberDto.MemberResponse read(String username) {
    Member member = memberDao.findByUsername(username).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
    return member.toReadDto();
  }

  public void updatePassword(MemberDto.UpdatePasswordRequest dto, String username) {
    Member member = memberDao.findByUsername(username).orElseThrow(()->new NoSuchElementException("사용자를 찾을 수 없습니다"));
    if(!passwordEncoder.matches(dto.getCurrentPassword(), member.getPassword()))
      throw new JobFailException("비밀번호를 변경할 수 없습니다");
    String newEncodedPassword = passwordEncoder.encode(dto.getNewPassword());
    memberDao.update(Member.builder().username(username).password(newEncodedPassword).build());
  }
}
