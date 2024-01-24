package com.nolan.repository;

import com.nolan.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 这里可以添加一些自定义的查询方法
}
