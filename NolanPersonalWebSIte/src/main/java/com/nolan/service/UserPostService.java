package com.nolan.service;

import com.nolan.bean.UserPost;
import com.nolan.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserPostService {
    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private ImageStorageService imageStorageService; // 用于存储图片的服务

    public List<UserPost> getUserPosts() {
        return userPostRepository.findAllByOrderByTimestampDesc();
    }

    public void createUserPost(UserPost userPost) {
        // 设置动态的发布时间
        userPost.setTimestamp(new Date());
        // 存储动态到数据库
        userPostRepository.save(userPost);
    }

    public List<String> saveImages(List<MultipartFile> images) throws IOException {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            // 保存图片到服务器，获取图片URL
            String imageUrl = imageStorageService.saveImage(image);
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }
}
