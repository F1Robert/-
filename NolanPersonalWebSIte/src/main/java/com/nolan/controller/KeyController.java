package com.nolan.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class KeyController {
    private final String secretKey = "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"; // 替换为实际的密钥
    @GetMapping("/get-key")
    public String getKey() {
        return secretKey;
    }
}
