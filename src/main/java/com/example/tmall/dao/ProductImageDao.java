package com.example.tmall.dao;

import com.example.tmall.entity.product.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductImageDao {
  @Insert("insert into product_image values(product_image_seq.nextval, #{name}, #{pno})")
  int insert(ProductImage productImage);
}
