package com.example.tmall.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class MemberDto {

  @Data
  @AllArgsConstructor
  public static class CreateRequest {
    @NotEmpty
    @Pattern(regexp="^[a-z0-9]{6,10}$", message="아이디는 소문자와 숫자 6~10자입니다")
    private String username;

    @NotEmpty
    @Pattern(regexp="^[A-Za-z0-9]{6,10}$", message="비밀번호는 영숫자 6~10자입니다")
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    private String profile;

    public void prePersist(String encodedPassword, String profile) {
      this.password = encodedPassword;
      this.profile = profile;
    }
  }

  @Data
  public static class ResetPasswordRequest {
    @NotEmpty(message="아이디는 필수입력입니다")
    @Pattern(regexp="^[a-z0-9]{6,10}$", message="아이디는 소문자와 숫자 6~10자입니다")
    private String username;
  }

  @Data
  public static class PasswordChangeRequest {
    @NotEmpty(message="기존 비밀번호는 필수입력입니다")
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$", message="현재 비밀번호는 영숫자 6~10자입니다")
    private String currentPassword;

    @NotEmpty(message="새 비밀번호는 필수입력입니다")
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$", message="새 비밀번호는 영숫자 6~10자입니다")
    private String newPassword;
  }

  @Data
  @AllArgsConstructor
  public static class MemberResponse {
    private String username;
    private String email;
    private String profileUrl;
    private String profileName;
    private String joinDay;
    private String birthday;
    // 가입기간
    private long days;
    private String level;
  }
}
