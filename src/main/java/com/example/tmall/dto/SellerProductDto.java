package com.example.tmall.dto;

import com.example.tmall.entity.product.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerProductDto {
  @Data
  public static class Create {
    @NotEmpty
    private String name;
    @NotEmpty
    private String info;
    @NotEmpty
    private List<MultipartFile> images;
    @NotNull
    private long price;
    @NotNull
    private long stock;

    public Product toEntity(String username) {
      return null;
    }
  }

  @Data
  public static class ProductDetail {
    private long id;
    private String name;
    private String info;
    private long price;
    private long salesCount;
    private long salesAmount;
    private Double rating;
    private long reviewCount;
    private long stock;
    private String seller;
    private List<ProductImage> images;
  }

  @Data
  public static class Update {
    @NotNull
    private long id;
    @NotEmpty
    private String info;
    @NotNull
    private long stock;
  }

  @Data
  public static class SellerSummary {
    private Integer id;
    private boolean orderExist;
    private String name;
    private String image;
    private Integer price;
    private Integer salesVolume;
    private Double star;
    private Integer reviewCount;
  }
}
