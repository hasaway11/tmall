package com.example.tmall.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
    String referer = req.getHeader("Referer");
    HttpSession session = req.getSession();
    session.setAttribute("msg", "로그인에 실패했습니다");
    if (referer != null && referer.contains("/seller/login")) {
      res.sendRedirect("/seller/login");
    } else {
      res.sendRedirect("/member/login");
    }
  }
}
