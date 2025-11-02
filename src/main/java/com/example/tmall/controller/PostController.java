package com.example.tmall.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class PostController {
  @GetMapping("/")
  public ModelAndView list() {
    return new ModelAndView("product/list");
  }
}
