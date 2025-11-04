package com.example.tmall;

import com.example.tmall.dao.*;
import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberDaoTest {
  @Autowired
  private MemberDao memberDao;

  //@Test
  public void initTest() {
    assertNotNull(memberDao);
  }

//  @Test
  public void insertTest() {
    MemberDto.CreateRequest dto = new MemberDto.CreateRequest("spring", "1234", "spring@naver.com", LocalDate.now(), "spring.jpg", MemberLevel.BRONZE);
    memberDao.insert(dto);
  }

  @Test
  public void updateTest() {
    Member member = new Member();
    member.setOrderCount(1);
    member.setOrderAmount(1200);
    member.setUsername("spring");
    memberDao.update(member);
  }
}
