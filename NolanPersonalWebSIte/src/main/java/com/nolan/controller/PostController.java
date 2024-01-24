package com.nolan.controller;

import com.nolan.bean.Post;
import com.nolan.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public String createPost(@RequestBody PostRequest postRequest) {
        String content = postRequest.getContent();
        List<String> imageDataArray = postRequest.getImageDataArray();
        postService.createPost(content, imageDataArray);
        return "rich_blog";
    }

    @GetMapping("/all")
    public String getAllPosts(Model model) {
        List<Post> postList = postService.getAllPosts();
        model.addAttribute("postList", postList);
        return "rich_blog"; // 这里返回 Thymeleaf 模板的名称
    }

    // 这是一个简单的请求体类，用于接收前端传递的数据
    public static class PostRequest {
        private String content;
        private List<String> imageDataArray;

        // 省略构造函数、getter 和 setter 方法

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImageDataArray() {
            return imageDataArray;
        }

        public void setImageDataArray(List<String> imageDataArray) {
            this.imageDataArray = imageDataArray;
        }
    }
}
