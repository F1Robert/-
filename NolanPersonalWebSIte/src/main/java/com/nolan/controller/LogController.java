package com.nolan.controller;

import com.nolan.bean.LogMessageBean;
import com.nolan.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {
    @Autowired
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/log")
    public String logMessage(@RequestBody String logMessage) {
        // 在这里处理接收到的 log message，可以将其记录到日志文件或执行其他操作
        logService.saveLogMessage(logMessage);
        // 返回响应
        return "Log message received successfully";
    }

    @GetMapping("/getLogs")
    public List<LogMessageBean> getLogs() {
        // 从数据库获取所有 log 数据
        return logService.getLogs();
    }

    @GetMapping("/searchLogs")
    public Page<LogMessageBean> searchLogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return logService.searchByKeyword(keyword, page, size);
    }
}
