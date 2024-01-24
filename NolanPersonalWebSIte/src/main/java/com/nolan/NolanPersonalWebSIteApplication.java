package com.nolan;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class NolanPersonalWebSIteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NolanPersonalWebSIteApplication.class, args);
    }


    @Test
    public void testPythonScript() {
        try {
            // 设置 Python 脚本路径
			String pythonScriptPath = "D:\\PythonProject\\pythonProject\\BuzzwordsAndMemesSpider\\generate.py";

            // 设置 Java 端传递给 Python 脚本的参数
            String inputText = "Hello from Java";

            // 构建 ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, inputText);

            // 启动进程
            Process process = processBuilder.start();

            // 读取标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();

            // 打印 Python 脚本的输出
            System.out.println("Python Script Output:");
            System.out.println(output.toString());

            // 打印 Python 脚本的退出代码
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
