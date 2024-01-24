package com.nolan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

@SpringBootTest
class NolanPersonalWebSIteApplicationTests {

	@Test
	void contextLoads() {
		try {
			// 本地代理服务器的地址和端口
			String proxyHost = "127.0.0.1";
			int proxyPort = 7890;
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new java.net.InetSocketAddress(proxyHost, proxyPort));

			// 定义目标 URL
			String apiUrl = "https://api.openai.com/v1/chat/completions";

			// 创建 URL 对象
			URL url = new URL(apiUrl);

			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

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

	@Test
	public void MyDraw(){
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				System.out.print("*  ");
			}
			System.out.println();
		}
	}
}
