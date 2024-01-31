package com.nolan.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 500)
    public String content;

    @ElementCollection
    @CollectionTable(name = "user_post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image")
    public List<String> images;

    @Temporal(TemporalType.TIMESTAMP)
    public Date timestamp;

    // getters and setters

    // Constructors, toString, hashCode, equals, etc.
}
