package com.example.tmall.service;

import com.example.tmall.dao.*;
import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import com.example.tmall.util.*;
import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
public class SellerService {
  @Autowired
  private SellerDao sellerDao;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MailUtil mailUtil;

  public void join(SellerDto.CreateRequest dto) {
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    String checkcode = RandomStringUtils.secure().nextAlphanumeric(10);
    dto.prePersist(encodedPassword, checkcode, SellerLevel.POWER);
    sellerDao.insert(dto);
    String text = "<a href='http://localhost:8080/seller/verify-checkcode?checkcode=" + checkcode + "'>클릭하세요</a>";
    mailUtil.sendMail("admin@icia.com", dto.getEmail(), "가입확인 메일입니다", text);
  }

  public boolean verifyCheckcode(String checkcode) {
    return sellerDao.activate(checkcode)==1;
  }
}
