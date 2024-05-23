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

    @GetMapping("/exhibition")
    public String showExhibitionPage() {
        return "exhibition";
    }

    @GetMapping("/logs")
    public String showLogs() {
        return "logs";
    }

    @GetMapping("/postPage")
    public String showPostPage() {
        return "post_page";
    }

    @GetMapping("/genshin")
    public String showGenshin() {
        return "genshin";
    }

    @GetMapping("/resume.html")
    public String showResumePage() {
        return "resume";
    }

    @GetMapping("/profile.html")
    public String showProfile() {
        return "profile";
    }

    @GetMapping("/iamBossGame.html")
    public String showIamBossPage() {
        return "iamBossGame";
    }

    @GetMapping("/game.html")
    public String showGamePage() {
        return "game";
    }

    @GetMapping("/game")
    public String showGame2Page() {
        return "game";
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
@GetMapping("/make_resume_2.html")
    public String makeResumePage2() {
        return "make_resume_2";
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
