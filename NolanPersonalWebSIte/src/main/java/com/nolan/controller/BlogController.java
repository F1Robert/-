package com.nolan.controller;

import com.nolan.bean.BlogPost;
import com.nolan.repository.BlogPostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// BlogController.java
@Controller
@RequestMapping("/")
public class BlogController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping("/blog")
    public String showBlogList(Model model) {
        List<BlogPost> posts = blogPostRepository.findAll();
        // 格式化日期时间为年月日时分格式
        if (!posts.isEmpty()) {
            BlogPost firstPost = posts.get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDate = firstPost.getCreatedAt().format(formatter);
            model.addAttribute("postTitle", firstPost.getTitle());
            model.addAttribute("postDate", formattedDate);
            model.addAttribute("postContent", firstPost.getContent());
        }
        model.addAttribute("posts", posts);
        return "nolan_blog";
    }

    @GetMapping("/post/{id}")
    public String showBlogPost(@PathVariable Long id, Model model, HttpServletRequest request) {
        BlogPost post = blogPostRepository.findById(id).orElse(null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = post.getCreatedAt().format(formatter);

        if (post != null) {
            model.addAttribute("postTitle", post.getTitle());
            model.addAttribute("postDate", formattedDate);
            model.addAttribute("postContent", post.getContent());
        }

        // 判断是否为 Ajax 请求
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            // 如果是 Ajax 请求，返回文章内容片段
            return "nolan_blog :: #blog-post";
        }

        // 否则返回整个页面
        List<BlogPost> posts = blogPostRepository.findAll();
        model.addAttribute("posts", posts);
        return "nolan_blog";
    }

    @GetMapping("/publish")
    public String showPublishForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "publish";
    }

    @PostMapping("/publish")
    public String publishBlogPost(@ModelAttribute BlogPost blogPost) {
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPostRepository.save(blogPost);
        return "redirect:/blog";
    }
}
