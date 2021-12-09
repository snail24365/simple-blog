package com.simple.blog.controller;

import com.simple.blog.model.User;
import com.simple.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

  @Autowired
  private UserService userService;

  @GetMapping("/sign-in")
  public String signInPage() {
    return "sign-in";
  }

  @GetMapping("/sign-up")
  public String signUpPage() {
    return "sign-up";
  }

  @PostMapping("/sign-up")
  public String signUp(User user, Model model) {
    if(user.isUsernameInvalid()) {
      final String message = "아이디는 5글자 이상이어야 합니다.";
      model.addAttribute("usernameErrorMessage", message);
    }
    if(user.isPasswordInvalid()) {
      final String message = "패스워드는 8글자 이상이어야 합니다.";
      model.addAttribute("passwordErrorMessage", message);
    }
    if(user.isNicknameInvalid()) {
      final String message = "닉네임은 2글자 이상이어야 합니다.";
      model.addAttribute("nicknameErrorMessage", message);
    }

    if(user.isUsernameInvalid() || user.isPasswordInvalid() || user.isNicknameInvalid()) {
      return "sign-up";
    }

    try {
      userService.register(user);
      return "redirect:/";
    } catch (DataIntegrityViolationException e) {
      final String message = "아이디가 이미 존재합니다. 다른 아이디를 사용해주세요.";
      model.addAttribute("signUpErrorMessage", message);
      return "sign-up";
    }
  }
}
