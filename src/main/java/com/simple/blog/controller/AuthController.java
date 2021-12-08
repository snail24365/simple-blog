package com.simple.blog.controller;

import com.simple.blog.model.User;
import com.simple.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
  @ResponseBody // 개발용
  public String signUp(User user) {
    userService.register(user);
    return "success";
  }
}
