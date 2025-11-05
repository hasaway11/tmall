package com.example.tmall.entity.product;

import lombok.*;

@Getter
public class Product {
  private long pno;
  private String seller;
  private String name;
  private String info;
  private long price;
  private long salesVolume;
  private long salesAmount;
  private long totalStar;
  private long reviewCount;
  private long stock;
}
