package com.gulaev.Selection_Room.repository;

import com.gulaev.Selection_Room.model.YoutubeVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideo, Integer> {

}
