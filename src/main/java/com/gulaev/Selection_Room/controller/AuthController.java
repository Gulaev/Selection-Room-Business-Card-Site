package com.gulaev.Selection_Room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/auth")
  public String loginPage() {
     return "login/login";
  }

}
