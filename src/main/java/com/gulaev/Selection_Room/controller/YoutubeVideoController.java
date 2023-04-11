package com.gulaev.Selection_Room.controller;

import com.google.api.services.youtube.YouTube.Videos;
import com.gulaev.Selection_Room.model.YoutubeVideo;
import com.gulaev.Selection_Room.service.YoutubeVideoService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YoutubeVideoController {

  private YoutubeVideoService youtubeVideoService;

  @Autowired
  public YoutubeVideoController(YoutubeVideoService youtubeVideoService) {
    this.youtubeVideoService = youtubeVideoService;
  }

  @GetMapping("/video")
  public String video(Model model) throws IOException {
    List<YoutubeVideo> allVideo = youtubeVideoService.getAllVideo();
    model.addAttribute("allVideo", allVideo);
    return "video";
  }

}
