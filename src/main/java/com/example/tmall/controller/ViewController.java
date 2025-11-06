package com.example.tmall.controller;

import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {
  @PreAuthorize("isAnonymous()")
  @GetMapping("/member/join")
  public void memberJoin() { }

  @PreAuthorize("isAnonymous()")
  @GetMapping("/member/find")
  public void find() { }

  @GetMapping("/member/login")
  public void memberLogin() { }

  @Secured("ROLE_MEMBER")
  @GetMapping("/member/update-password")
  public void updatePassword() { }

  @GetMapping("/seller/join")
  public void sellerJoin() { }

  @GetMapping("/seller/product/register")
  public String register() {
    return "product/register";
  }
}
