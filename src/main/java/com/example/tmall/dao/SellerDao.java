package com.example.tmall.dao;

import com.example.tmall.dto.*;
import com.example.tmall.entity.account.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface SellerDao {
  @Select("select count(*) from seller where username=#{username} and rownum=1")
  boolean existsByUsername(String username);

  int insert(Seller seller);

  @Select("select * from seller where username=#{username}")
  Optional<Seller> findByUsername(String username);

  int update(Member member);

  int activate(String checkcode);

  @Delete("delete from member where username=#{username}")
  int delete(String username);
}
