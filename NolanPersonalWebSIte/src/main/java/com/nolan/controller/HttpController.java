package com.nolan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;

@RestController
public class HttpController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/api/sendMessage")
    @CrossOrigin(origins = "*")
    public String sendMessage(@RequestBody String message) {
        try {
            // 本地代理服务器的地址和端口
            String proxyHost = "127.0.0.1";
            int proxyPort = 7890;
            // 设置代理
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(proxyHost, proxyPort)));
            restTemplate.setRequestFactory(requestFactory);
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("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");
            // headers.set("Authorization", "Bearer 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");
            // 构建请求体
            // 使用字符串插值
            //                "model": "gpt-3.5-turbo-1106", "model": "gpt-4-1106-preview",
            String requestBody = """
                    {
                        "model": "gpt-3.5-turbo-1106",          
                        "messages": [
                            {
                                "role": "user",
                                "content": %s
                            }
                        ]
                    }
                    """.formatted(message);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            // 模拟发送消息到外部 API（这里使用 httpbin.org 作为示例）
            String apiUrl = "https://api.openai.com/v1/chat/completions";
            System.out.println(request);

            String response = restTemplate.postForObject(apiUrl, request, String.class);
            // 处理响应，这里简单地返回响应结果
            System.out.println(response);
            return response;
        } catch (Exception e) {
            // 发生错误时打印错误信息
            System.err.println("Error in sending message: " + e.getMessage());
            return "Error";
        }
    }


    public void getResponse() {
        try {
            // 定义目标 URL
            String apiUrl = "https://api.openai.com/v1/chat/completions";

            // 创建 URL 对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");

            // 启用输入输出流
            connection.setDoOutput(true);

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            // 构建请求体数据
            String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + "123" + "\"}]}";

            // 获取输出流并写入请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            // 读取响应内容
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response Code: " + responseCode);
                System.out.println("Response Content: " + response.toString());
            }

            // 关闭连接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

