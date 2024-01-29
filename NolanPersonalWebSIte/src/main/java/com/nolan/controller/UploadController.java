package com.nolan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public String handleUdpUpload(String data) {
        // 处理接收到的UDP数据，您可以在这里进行业务逻辑处理
        System.out.println("Received UDP data: " + data);

        // 返回响应
        return "UDP Upload successful";
    }

    // 启动一个UDP监听服务
    @Controller
    static class UdpListener {

        private static final int UDP_PORT = 8080;

        @SuppressWarnings("InfiniteLoopStatement")
        UdpListener() {
            new Thread(() -> {
                try {
                    DatagramSocket socket = new DatagramSocket(UDP_PORT);

                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                        // 从数据包中提取数据
                        String receivedData = new String(packet.getData(), 0, packet.getLength());

                        // 处理接收到的UDP数据，您可以在这里进行业务逻辑处理
                        System.out.println("Received UDP data: " + receivedData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
