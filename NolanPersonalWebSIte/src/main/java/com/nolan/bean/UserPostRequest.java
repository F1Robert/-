package com.nolan.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserPostRequest {

    private String content;
    private List<MultipartFile> images;

    // Constructors, getters, and setters

    public UserPostRequest() {
        // 默认构造函数
    }

    public UserPostRequest(String content, List<MultipartFile> images) {
        this.content = content;
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
