package com.example.tmall.security;

import com.example.tmall.dao.*;
import com.example.tmall.entity.account.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private MemberDao memberDao;
  @Autowired
  private SellerDao sellerDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String referer = request.getHeader("Referer");
    if(referer !=null && referer.contains("/member/login")) {
      Member m = memberDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
      return User.builder().username(username).password(m.getPassword()).roles(Role.MEMBER.name()).build();
    } else if(referer!=null && referer.contains("/seller/login")) {
      Seller s = sellerDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
      return User.builder().username(username).password(s.getPassword()).roles(Role.SELLER.name()).build();
    } else
      throw new UsernameNotFoundException("잘못된 로그인 경로입니다");
  }
}
