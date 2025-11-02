package com.example.tmall;

import ch.qos.logback.core.pattern.color.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.*;
import org.springframework.security.web.authentication.*;

// 1. 로그인이 필요한 작업을 요청하면 요청 경로가 /seller로 시작하면 /seller/login으로, 기타 요청 경로는 /member/login으로 (AuthenticationEntryPoint)
// 2. 권한이 잘못된 경우도 요청 경로에 따라 1번과 동일하게 이동 (AccessDeniedHandler)
// 3. 로그인이 실패한 경우도 요청 경로에 따라 1번과 동일하게 이동 (AuthenticationFailureHandler)
// 4. 로그인 성공 후 로그인을 처리한 주소에 따라 seller면 /seller/main으로, /member인 경우 /로 이동(AuthenticationSuccessHandler)

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
  @Autowired
  private AuthenticationEntryPoint entryPoint;
  @Autowired
  private AccessDeniedHandler deniedHandler;
  @Autowired
  private AuthenticationSuccessHandler successHandler;
  @Autowired
  private AuthenticationFailureHandler failureHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity config) throws Exception {
    config.csrf(c-> c.disable());
    config.formLogin(c->c.loginPage("/login").loginProcessingUrl("/login").successHandler(successHandler).failureHandler(failureHandler));
    config.logout(c->c.logoutUrl("/logout").logoutSuccessUrl("/"));
    config.exceptionHandling(c->c.accessDeniedHandler(deniedHandler).authenticationEntryPoint(entryPoint));
    return config.build();
  }
}
