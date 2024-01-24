package com.nolan.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// BlogPost.java
@Entity
@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT") // Allow large text for content
    private String content;
    private LocalDateTime createdAt;
    // getters and setters
}