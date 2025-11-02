package com.example.tmall.entity.account;

import com.example.tmall.dto.*;
import lombok.*;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  private String username;
  private String password;
  private String email;
  private LocalDate joinDay;
  private LocalDate birthday;
  private String profile;
  private MemberLevel level;
  private long orderCount;
  private long orderAmount;

  public MemberDto.MemberResponse toReadDto() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    String joinDayStr = dtf.format(this.joinDay);
    String birthdayStr = dtf.format(this.birthday);
    long days = ChronoUnit.DAYS.between(joinDay, LocalDate.now());
    return new MemberDto.MemberResponse(username, email, "/profile/" + profile, profile, joinDayStr, birthdayStr, days, level.name());
  }
}
