package com.example.tmall.dao;

import com.example.tmall.dto.*;
import com.example.tmall.entity.product.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface ProductDao {
  @SelectKey(statement="select product_seq.nextval from dual", keyProperty="pno", before=true, resultType=int.class)
  @Insert("insert into product values(#{pno}, #{seller}, #{name}, #{info}, #{price}, 0, 0, 0, 0, #{stock})")
  int save(Product product);

  ProductDto.ReadResponse findByPno(long pno);
}
