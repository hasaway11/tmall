package com.example.tmall.entity.account;

import lombok.*;

import java.time.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seller {
  private String username;
  private String password;
  private String email;
  private String introduction;
  private LocalDate joinDay;
  private SellerLevel level;
  private long salesCount;
  private long salesAmount;
  private boolean activated;
  private String checkcode;

}
