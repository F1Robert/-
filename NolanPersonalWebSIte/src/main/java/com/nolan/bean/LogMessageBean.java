package com.nolan.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LogMessageBean {
    String message;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
