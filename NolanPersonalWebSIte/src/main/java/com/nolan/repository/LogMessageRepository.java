package com.nolan.repository;

import com.nolan.bean.LogMessageBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogMessageRepository extends JpaRepository<LogMessageBean, Long> {
    // 这里可以添加自定义的查询方法，例如按照 message 查询

    // 自定义查询方法，使用 @Query 注解指定 JPQL 查询语句
    @Query("SELECT log FROM LogMessageBean log WHERE log.message LIKE %:keyword%")
    Page<LogMessageBean> findByKeyword(String keyword, Pageable pageable);
}
