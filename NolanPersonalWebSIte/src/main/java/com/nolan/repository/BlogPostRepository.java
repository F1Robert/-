package com.nolan.repository;

import com.nolan.bean.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // 这里不需要手动实现 CRUD 方法，JpaRepository 会自动提供
}
