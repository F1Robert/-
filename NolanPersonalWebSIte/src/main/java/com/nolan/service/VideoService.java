package com.nolan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoService {

    @Value("${video.folder.path}")
    private String videoFolderPath;

    public List<String> getVideoList() {
        File folder = new File(videoFolderPath);
        if (folder.exists() && folder.isDirectory()) {
            String[] videoFiles = folder.list((dir, name) -> name.toLowerCase().endsWith(".mp4"));
            return Arrays.asList(videoFiles);
        }
        return null;
    }
}
