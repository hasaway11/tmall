package com.example.tmall.dao;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class PageResponse<T> {
  private List<T> contents;
  private long prev;
  private long next;
  private long pageno;
  private List<Long> pages;
}
