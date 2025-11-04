package com.example.tmall.dto;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class PageResponse {
  private List<ProductDto.ListResponse> products;
  private long prev;
  private long start;
  private long end;
  private long next;
}
