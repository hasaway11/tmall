package com.example.tmall.controller.member;

import com.example.tmall.dto.*;
import com.example.tmall.service.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import java.security.*;

@Controller
public class MemberController {
  @Autowired
  private MemberService memberService;

  @PreAuthorize("isAnonymous()")
  @GetMapping("/api/member/check-username")
  public ResponseEntity<Void> 아이디_사용여부_확인(@RequestParam @NotEmpty String username) {
    boolean result = memberService.checkUsername(username);
    return result? ResponseEntity.ok(null) : ResponseEntity.status(HttpStatus.CONFLICT).body(null);
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/member/join")
  public ModelAndView join(@ModelAttribute @Valid MemberDto.CreateRequest dto) {
    memberService.join(dto);
    return new ModelAndView("redirect:/member/login");
  }

  @PreAuthorize("isAnonymous()")
  @GetMapping("/api/member/username")
  public ResponseEntity<String> 아이디_찾기(@RequestParam @NotEmpty @Email String email) {
    return ResponseEntity.ok(memberService.findUsername(email));
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/api/member/password")
  public ResponseEntity<String> resetPassword(@ModelAttribute @Valid MemberDto.ResetPasswordRequest dto) {
    memberService.resetPassword(dto);
    return ResponseEntity.ok("임시비밀번호를 가입 이메일로 보냈습니다");
  }

  @Secured("ROLE_MEMBER")
  @GetMapping("/member/verify-password")
  public String verifyPassword(HttpSession session) {
    if(session.getAttribute("isPasswordVerified")!=null)
      return "redirect:/member/read";
    return "member/vefify-password";
  }

  @Secured("ROLE_MEMBER")
  @PostMapping("/member/verify-password")
  public ModelAndView checkPassword(@RequestParam @NotEmpty String password, Principal principal, RedirectAttributes ra, HttpSession session) {
    boolean isPasswordVerified = memberService.verifyPassword(password, principal.getName());
    if(isPasswordVerified) {
      session.setAttribute("isPasswordVerified", true);
      return new ModelAndView("redirect:/member/read");
    } else {
      ra.addFlashAttribute("msg", "사용자를 확인하지 못했습니다");
      return new ModelAndView("redirect:/member/vefify-password");
    }
  }

  @Secured("ROLE_MEMBER")
  @GetMapping("/member/read")
  public ModelAndView read(Principal principal, HttpSession session) {
    if(session.getAttribute("isPasswordVerified")==null)
      return new ModelAndView("redirect:/member/verify-password");
    MemberDto.MemberResponse dto = memberService.read(principal.getName());
    return new ModelAndView("member/read").addObject("member", dto);
  }

  @Secured("ROLE_MEMBER")
  @PostMapping("/member/update-password")
  public ModelAndView updatePassword(@ModelAttribute @Valid MemberDto.UpdatePasswordRequest dto, Principal principal) {
    memberService.updatePassword(dto, principal.getName());
    return new ModelAndView("redirect:/");
  }
}
