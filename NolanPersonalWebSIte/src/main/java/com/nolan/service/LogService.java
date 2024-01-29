package com.nolan.service;

import com.nolan.bean.LogMessageBean;
import com.nolan.repository.LogMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogMessageRepository logMessageRepository;

    @Autowired
    public LogService(LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }

    public void saveLogMessage(String logMessage) {
        LogMessageBean logMessageBean = new LogMessageBean();
        logMessageBean.setMessage(logMessage);
        logMessageRepository.save(logMessageBean);
    }

    public List<LogMessageBean> getLogs() {
        return logMessageRepository.findAll();
    }


    public Page<LogMessageBean> searchByKeyword(String keyword, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return logMessageRepository.findByKeyword(keyword, pageable);
    }
}
