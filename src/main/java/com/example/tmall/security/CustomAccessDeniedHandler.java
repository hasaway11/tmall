package com.example.tmall.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
    String uri = req.getRequestURI();
    if (uri.startsWith("/seller")) {
      res.sendRedirect("/seller/login");
    } else {
      res.sendRedirect("/member/login");
    }
  }
}
