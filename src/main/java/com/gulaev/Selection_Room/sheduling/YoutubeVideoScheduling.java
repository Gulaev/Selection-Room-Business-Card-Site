package com.gulaev.Selection_Room.sheduling;

import com.gulaev.Selection_Room.service.YoutubeVideoService;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YoutubeVideoScheduling extends TimerTask {

  private YoutubeVideoService youtubeVideoService;

  @Autowired
  public YoutubeVideoScheduling(YoutubeVideoService youtubeVideoService) {
    this.youtubeVideoService = youtubeVideoService;
  }


  @Override
  public void run() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);

      executorService.execute(() -> {
        try {
          youtubeVideoService.getAllVideoAndSaveToDb();

        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });

  }
}
