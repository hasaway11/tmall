package com.example.tmall.dto;

import com.example.tmall.entity.product.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {
  @Data
  public static class ListResponse {
    private long id;
    private String seller;
    private String name;
    private String image;
    private long price;
    private long salesVolume;
    private Double star;
    private long reviewCount;
  }

  @Data
  public static class ReadResponse {
    private long pno;
    private String name;
    private String info;
    private long price;
    private long reviewCount;
    private long stock;
    private String seller;
    private long totalRating;
    private List<String> images;
    private List<Review> reviews;
  }
}
