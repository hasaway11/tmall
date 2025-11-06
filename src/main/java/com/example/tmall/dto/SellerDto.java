package com.example.tmall.dto;

import com.example.tmall.entity.account.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class SellerDto {
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

    private String introduction;

    private String checkcode;

    private SellerLevel level;

    public Seller toEntity(String encodedPassword, String checkcode, SellerLevel sellerLevel) {
      return Seller.builder().username("c##" + username).password(encodedPassword).email(email).introduction(introduction)
          .checkcode(checkcode).level(sellerLevel).build();
    }
  }
}
