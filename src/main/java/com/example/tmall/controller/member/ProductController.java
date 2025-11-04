package com.example.tmall.controller.member;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class ProductController {
  @GetMapping({"/", "/product/list"})
  public ModelAndView list() {
    return new ModelAndView("product/list");
  }

  @GetMapping("/product/read")
  public void read() {
  }
}
