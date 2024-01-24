package com.nolan.repository;

import com.nolan.bean.ContentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<ContentEntity, Long> {
}
