package com.example.tmall.dao;

import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SellerDao {
  @Select("select count(*) from seller where username=#{username} and rownum=1")
  boolean existsByUsername(String username);

  int insert(SellerDto.CreateRequest dto);

  @Select("select * from seller username=#{username}")
  Member findByUsername(String username);

  int update(Member member);

  int activate(String username);

  @Delete("delete from member where username=#{username}")
  int delete(String username);
}
