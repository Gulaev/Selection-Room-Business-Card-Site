package com.gulaev.Selection_Room.service;

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
import com.gulaev.Selection_Room.model.YoutubeVideo;
import com.gulaev.Selection_Room.repository.YoutubeVideoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class YoutubeVideoService {

  private YoutubeVideoRepository youtubeVideoRepository;
  @Value("${google.id}")
  private  String channelId;
  @Value("${google.key}")
  private  String apiKey;
  private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private final JsonFactory JSON_FACTORY = new JacksonFactory();

  @Autowired
  public YoutubeVideoService(YoutubeVideoRepository youtubeVideoRepository) {
    this.youtubeVideoRepository = youtubeVideoRepository;
  }

  public void getAllVideoAndSaveToDb() throws IOException {
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        new HttpRequestInitializer() {
          @Override
          public void initialize(HttpRequest httpRequest) throws IOException {}
        }).setApplicationName("SelectionRoom").setYouTubeRequestInitializer(
        new YouTubeRequestInitializer(apiKey)).build();

    YouTube.Search.List request = youtube.search().list("snippet");
    request.setChannelId(channelId);
    request.setType("video");
    request.setMaxResults(50L); // maximum number of results to retrieve per API request
    SearchListResponse response = request.execute();

    List<YoutubeVideo> videoList = new ArrayList<>();
    List<SearchResult> items = response.getItems();

    for (SearchResult item : items) {
      String videoId = item.getId().getVideoId();
      String title = item.getSnippet().getTitle();
      String description = item.getSnippet().getDescription();
      String videoUrl = "https://www.youtube.com/embed/" + videoId;
      videoList.add(new YoutubeVideo(videoUrl, title, description));
    }

    loadToDb(videoList);
  }


  private void loadToDb(List<YoutubeVideo> youtubeVideos) {
    youtubeVideoRepository.saveAll(youtubeVideos);
  }

  public List<YoutubeVideo> getAllVideo() {
    return youtubeVideoRepository.findAll();
  }
}

