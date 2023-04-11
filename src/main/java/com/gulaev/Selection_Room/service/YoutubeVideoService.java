package com.gulaev.Selection_Room.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.gulaev.Selection_Room.model.YoutubeVideo;
import com.gulaev.Selection_Room.repository.YoutubeVideoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoutubeVideoService {

  private YoutubeVideoRepository youtubeVideoRepository;
  private final String channelId;
  private final String apiKey;
  private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private final JsonFactory JSON_FACTORY = new JacksonFactory();

  @Autowired
  public YoutubeVideoService(YoutubeVideoRepository youtubeVideoRepository) {
    this.youtubeVideoRepository = youtubeVideoRepository;
    this.channelId = "UCG-dmsed2RhkmCVdOC3mj-Q";
    this.apiKey = "AIzaSyA4AcJwTFqawSBcwhjmC93CjNWKazE8Yp4";
  }

  public List<String> getAllCurrentVideo() throws IOException {
    YouTube youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        new HttpRequestInitializer() {
          @Override
          public void initialize(HttpRequest httpRequest) throws IOException {

          }
        }).setApplicationName("SelectionRoom").setYouTubeRequestInitializer(
        new YouTubeRequestInitializer("AIzaSyA4AcJwTFqawSBcwhjmC93CjNWKazE8Yp4")).build();

    YouTube.Search.List search = youTube.search().list("id,snippet");

    search.setKey(apiKey);
    search.setChannelId("UCG-dmsed2RhkmCVdOC3mj-Q");
    search.setType("video");
    search.setOrder("date");
    search.setMaxResults(50L);
    SearchListResponse response = search.execute();
    List<SearchResult> searchResults = response.getItems();
    //searchResults.forEach(System.out::println);

    List<String> videoUrls = new ArrayList<>();
    for (SearchResult searchResult : searchResults) {
      String videoId = searchResult.getId().getVideoId();
      String videoUrl = "https://www.youtube.com/embed/" + videoId;
      videoUrls.add(videoUrl);
    }

    videoUrls.stream().forEach(System.out::println);
    return videoUrls;
  }


  public List<YoutubeVideo> getAllVideo() throws IOException {
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        new HttpRequestInitializer() {
          @Override
          public void initialize(HttpRequest httpRequest) throws IOException {

          }
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

    return videoList;
  }
}

