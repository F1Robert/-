package com.nolan.controller;

import com.nolan.bean.Position;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {
    @PostMapping("/gps/upload")
    public String receiveData(@RequestBody Position requestData) {
        // 在这里处理从前端接收到的数据
        System.out.println("收到位置数据: " + requestData);
        // 返回响应给前端
        return "Data received successfully";
    }

    @PostMapping("/gps/test")
    public String receiveData(@RequestBody String requestData) {
        // 在这里处理从前端接收到的数据
        System.out.println("收到位置数据: " + requestData);
        // 返回响应给前端
        return "Data received successfully";
    }
}
