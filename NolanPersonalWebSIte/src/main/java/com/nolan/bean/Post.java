package com.nolan.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 在实际项目中，你可能需要使用 Lob 注解来存储大字段
    @ElementCollection
    private List<String> imageDataArray;

    // 省略构造函数、getter 和 setter 方法

    // Getters and setters
}
