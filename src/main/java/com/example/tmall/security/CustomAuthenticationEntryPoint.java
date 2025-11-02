package com.example.tmall.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
    String uri = req.getRequestURI();
    if (uri.startsWith("/seller")) {
      res.sendRedirect("/seller/login");
    } else {
      res.sendRedirect("/member/login");
    }
  }
}
