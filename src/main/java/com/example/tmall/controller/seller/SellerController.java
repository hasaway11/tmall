package com.example.tmall.controller.seller;

import com.example.tmall.dto.*;
import com.example.tmall.service.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

@Controller
public class SellerController {
  @Autowired
  private SellerService sellerService;

  @PostMapping("/api/seller/new")
  public ResponseEntity<Void> join(@ModelAttribute @Valid SellerDto.CreateRequest dto) {
    sellerService.join(dto);
    return ResponseEntity.ok(null);
  }

  @GetMapping("/seller/verify-checkcode")
  public ModelAndView verifyCheckcode(@RequestParam String checkcode, RedirectAttributes ra) {
    boolean result = sellerService.verifyCheckcode(checkcode);
    if(result) {
      ra.addFlashAttribute("msg", "가입을 환영합니다");
      return new ModelAndView("redirect:/seller/login");
    } else {
      return new ModelAndView("system/error").addObject("msg", "유효하지 않은 체크코드입니다.");
    }
  }

  @GetMapping("/seller/login")
  public void login() {
  }
}
