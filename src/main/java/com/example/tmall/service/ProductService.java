package com.example.tmall.service;

import com.example.tmall.dao.*;
import com.example.tmall.dto.SellerProductDto;
import com.example.tmall.entity.product.Product;
import com.example.tmall.entity.product.ProductImage;
import com.example.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  private ProductDao productDao;
  @Autowired
  private ProductImageDao productImageDao;

  @Transactional
  public Product create(SellerProductDto.Create dto, String username) {
    Product product = dto.toEntity(username);
    List<String> imageNames = ImageUtil.savaProductImage(dto.getImages());
    productDao.save(product);
    for(String imageName:imageNames) {
      ProductImage pi = new ProductImage(0, imageName, product.getPno());
      productImageDao.insert(pi);
    }
    return product;
  }
}
