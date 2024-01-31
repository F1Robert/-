package com.nolan.controller;

import com.nolan.bean.UserPost;
import com.nolan.bean.UserPostRequest;
import com.nolan.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/index")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;

    @PostMapping("/post")
    public ResponseEntity<?> createUserPost(@RequestParam("content") String content,
                                            @RequestParam("images") List<MultipartFile> images) throws IOException {
        // 处理图片上传
        List<String> imageUrls = userPostService.saveImages(images);

        // 创建 UserPost 对象并保存到数据库
        UserPost userPost = new UserPost();
        userPost.setContent(content);
        userPost.setImages(imageUrls);
        userPost.setTimestamp(new Date());

        userPostService.createUserPost(userPost);

        return ResponseEntity.ok("User post created successfully");
    }
}
