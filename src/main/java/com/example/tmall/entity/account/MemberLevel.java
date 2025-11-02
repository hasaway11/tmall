package com.example.tmall.entity.account;

import lombok.*;

@Getter
@AllArgsConstructor
public enum MemberLevel {
  BRONZE("고마운분"), SILVER("소중한분"), GOLD("천생연분");

  private final String label;
}
