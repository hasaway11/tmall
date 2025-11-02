package com.example.tmall.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
