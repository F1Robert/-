package com.nolan.controller;

import com.nolan.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.List;

@Controller
public class VideoController {

    private final String videoFolderPath = "C:/Users/86156/Downloads/Telegram Desktop";

    @GetMapping("/{videoName}")
    public ResponseEntity<FileSystemResource> getVideo(@PathVariable String videoName) {
        File videoFile = new File(videoFolderPath + "/" + videoName);
        if (videoFile.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("video/mp4"))
                    .body(new FileSystemResource(videoFile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private VideoService videoService;

    @GetMapping("/videos")
    public String index(Model model) {
        List<String> videoList = videoService.getVideoList();
        model.addAttribute("videoList", videoList);
        return "videos";
    }

    @GetMapping("/play/{video}")
    public String playVideo(@PathVariable String video, Model model) {
        model.addAttribute("video", video);
        model.addAttribute("videoFolderPath", videoFolderPath);
        return "play";
    }
}
