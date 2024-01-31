package com.nolan.controller;

import com.nolan.bean.UserPost;
import com.nolan.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserPostService userService;  // 假设有一个 UserService 类用于获取用户数据

    @GetMapping("/profile")
    public String userProfile(Model model) {
        // 获取用户介绍
//        String userIntroduction = userService.getUserIntroduction();
        // 获取用户动态列表
        List<UserPost> userPosts = userService.getUserPosts();
        // 将数据传递到模板中
        model.addAttribute("userPosts", userPosts);
        // 返回模板文件的名称
        return "profile";
    }
}

