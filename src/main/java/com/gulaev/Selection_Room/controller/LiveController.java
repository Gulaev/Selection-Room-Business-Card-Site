package com.gulaev.Selection_Room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class LiveController {


  @GetMapping("/live")
  public String livePage(Model model) {
    return "live";
  }




}
