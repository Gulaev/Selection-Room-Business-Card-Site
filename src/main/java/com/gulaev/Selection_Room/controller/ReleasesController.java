package com.gulaev.Selection_Room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReleasesController {

  @GetMapping("/releases")
  public String releases(Model model) {
    return "releases";
  }

}
