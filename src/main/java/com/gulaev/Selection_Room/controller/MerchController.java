package com.gulaev.Selection_Room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MerchController {

  @GetMapping("/merch")
  public String merch(Model model) {
    return "merch";
  }
}
