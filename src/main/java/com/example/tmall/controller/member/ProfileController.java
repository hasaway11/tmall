package com.example.tmall.controller.member;

import com.example.tmall.service.*;
import com.example.tmall.util.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

@Controller
public class ProfileController {
  @Autowired
  private MemberService memberService;

  @PostConstruct
  public void makeFolder() {
    File uploadFolder = new File(ProfileUtil.UPLOAD_FOLDER);
    File tempFolder = new File(ProfileUtil.TEMP_FOLDER);
    File profileFolder = new File(ProfileUtil.PROFILE_FOLDER);
    if(!uploadFolder.exists())
      uploadFolder.mkdir();
    if(!tempFolder.exists())
      tempFolder.mkdir();
    if(!profileFolder.exists())
      profileFolder.mkdir();
  }

  @PostMapping("/api/profile/new")
  public ResponseEntity<Map<String, String>> uploadProfile(MultipartFile profile) {
    try {
      if (profile != null && !profile.isEmpty()) {
        File dest = new File(ProfileUtil.TEMP_FOLDER, profile.getOriginalFilename());
        profile.transferTo(dest);
      }
    } catch(IOException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    String profileName = profile.getOriginalFilename();
    Map<String,String> map = Map.of("profileUrl", "/api/temp/" + profileName, "profileName", profileName);
    return ResponseEntity.ok(map);
  }

  private ResponseEntity<byte[]> readProfile(String fileName, String folderName) {
    try {
      File file = new File(folderName, fileName);
      byte[] imageBytes = Files.readAllBytes(file.toPath());
      MediaType mediaType = ProfileUtil.getMediaType(fileName);
      return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/api/temp/{fileName}")
  public ResponseEntity<byte[]> getTempProfile(@PathVariable String fileName) {
    return readProfile(fileName, ProfileUtil.TEMP_FOLDER);
  }

  @GetMapping("/api/profile/{fileName}")
  public ResponseEntity<byte[]> getProfile(@PathVariable String fileName) {
    return readProfile(fileName, ProfileUtil.PROFILE_FOLDER);
  }

  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/member/profile")
  public ResponseEntity<String> changeProfile(@RequestParam String profile, Principal principal) {
    memberService.updateProfile(profile, principal.getName());
    return ResponseEntity.ok("프로필 사진을 변경했습니다");
  }
}