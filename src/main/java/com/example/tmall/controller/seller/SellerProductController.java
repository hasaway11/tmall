package com.example.tmall.controller.seller;

import com.example.tmall.dto.*;
import com.example.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Secured("ROLE_SELLER")
@Controller
public class SellerProductController {
  @Autowired
  private ProductService service;



  @PostMapping("/seller/product/register")
  public ModelAndView register(SellerProductDto.Create dto) {
    System.out.println(dto);
    return null;
  }

  @GetMapping("/seller/main")
  public ModelAndView main() {
    return null;
  }

  public ModelAndView read() {
    return null;
  }

  public ModelAndView update() {
    return null;
  }

  public ModelAndView delete() {
    return null;
  }
}
