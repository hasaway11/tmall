package com.example.tmall.entity.product;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
public class Review {
  private long id;
  private String writer;
  private String content;
  private long rating;
  @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
  private LocalDateTime writeTime;
  private long productId;
}
