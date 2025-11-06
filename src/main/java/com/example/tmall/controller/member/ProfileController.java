package com.example.tmall.controller.member;

import com.example.tmall.service.*;
import com.example.tmall.util.*;
import jakarta.annotation.*;
import jakarta.validation.constraints.*;
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

  @PostMapping("/api/profile/new")
  public ResponseEntity<Map<String, String>> 프로필_사진_업로드(MultipartFile profile) {
    try {
      if (profile != null && !profile.isEmpty()) {
        File dest = new File(ImageUtil.TEMP_FOLDER, profile.getOriginalFilename());
        profile.transferTo(dest);
      }
    } catch(IOException e) {
      return ResponseEntity.status(409).body(null);
    }
    String profileName = profile.getOriginalFilename();
    Map<String,String> map = Map.of("profileUrl", "/api/temp/" + profileName, "profileName", profileName);
    return ResponseEntity.ok(map);
  }

  @GetMapping("/api/temp/{fileName}")
  public ResponseEntity<byte[]> getTempProfile(@PathVariable String fileName) {
    return ImageUtil.readProfile(fileName, ImageUtil.TEMP_FOLDER);
  }

  @GetMapping("/api/profile/{fileName}")
  public ResponseEntity<byte[]> getProfile(@PathVariable String fileName) {
    return ImageUtil.readProfile(fileName, ImageUtil.PROFILE_FOLDER);
  }

  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/member/profile")
  public ResponseEntity<String> changeProfile(@RequestParam @NotEmpty String profile, Principal principal) {
    memberService.changeProfile(profile, principal.getName());
    return ResponseEntity.ok("프로필 사진을 변경했습니다");
  }
}