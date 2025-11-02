package com.example.tmall.controller;

import com.example.tmall.dto.*;
import com.example.tmall.service.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SellerController {
  @Autowired
  private SellerService sellerService;

  public ResponseEntity<String> join(@ModelAttribute @Valid SellerDto.CreateRequest dto) {
    sellerService.join(dto);
    return ResponseEntity.ok(null);
  }
}
