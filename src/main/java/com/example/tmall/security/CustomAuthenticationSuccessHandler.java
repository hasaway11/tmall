package com.example.tmall.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
    String referer = req.getHeader("Referer");
    if (referer != null && referer.contains("/seller/login")) {
      res.sendRedirect("/seller/main");
    } else {
      res.sendRedirect("/");
    }
  }
}
