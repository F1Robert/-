package com.nolan.repository;

import com.nolan.bean.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findAllByOrderByTimestampDesc();
}
