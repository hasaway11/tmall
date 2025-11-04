package com.example.tmall.controller.seller;

import com.example.tmall.dto.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Secured(("ROLE_SELLER"))
@Controller
public class SellerProductController {
  @GetMapping("/seller/product/register")
  public void register() { }

  @GetMapping("/api/seller/products/new")
  public ResponseEntity register(SellerProductDto.Create dto) {
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
