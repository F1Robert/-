package com.nolan.controller;

import com.nolan.bean.ContentEntity;
import com.nolan.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*")
public class MainPageController {
    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/resume.html")
    public String showResumePage() {
        return "resume";
    }

    @GetMapping("/nolan_gpt.html")
    public String showNolanGptPage() {
        return "nolan_gpt";
    }

    @GetMapping("/gpt")
    public String showNolan_GptPage() {
        return "nolan_gpt";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/bilibili.html")
    public String showBilibiliGptPage() {
        return "bilibili";
    }

    @GetMapping("/n_audio_player.html")
    public String showNAudioPlayerPage() {
        return "n_audio_player";
    }

    @GetMapping("/make_resume.html")
    public String makeResumePage() {
        return "make_resume";
    }

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/rich_blog")
    public String showContents(Model model) {
        Iterable<ContentEntity> contents = contentRepository.findAll();
        model.addAttribute("contents", contents);
        return "rich_blog";
    }
}
