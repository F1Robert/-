package com.nolan.controller;

import com.nolan.bean.ContentEntity;
import com.nolan.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorController {

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/editor")
    public String showEditor() {
        return "editor";
    }

    @PostMapping("/save-content")
    public String saveContent(@RequestParam String htmlContent) {
        // 创建一个实体类并保存 HTML 内容到数据库
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContent(htmlContent);
        contentRepository.save(contentEntity);

        // 重定向到编辑页面或其他适当的页面
        return "redirect:/rich_blog";
    }
}
