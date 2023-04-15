package com.gulaev.Selection_Room;


import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.gulaev.Selection_Room.service.YoutubeVideoService;
import com.gulaev.Selection_Room.sheduling.YoutubeVideoScheduling;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelectionRoomApplication {


  @Autowired
  private YoutubeVideoScheduling youtubeVideoScheduling;

  public static void main(String[] args) {
    SpringApplication.run(SelectionRoomApplication.class, args);
  }


  @PostConstruct
  private void run() throws IOException {
    Timer timer = new Timer();
    timer.schedule(youtubeVideoScheduling, 0, 900000);

  }

}