package com.example.tmall.dao;

import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface MemberDao {
  @Select("select count(*) from member where username=#{username} and rownum=1")
  boolean existsByUsername(String username);

  int insert(MemberDto.CreateRequest member);

  @Select("select * from member username=#{username}")
  Optional<Member> findByUsername(String username);

  @Select("select username from member email=#{email}")
  Optional<String> findUsernameByEmail(String email);

  int update(Member member);

  @Delete("delete from member where username=#{username}")
  int delete(String username);
}
