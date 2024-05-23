package com.nolan;

import com.nolan.bean.CommDataInfo;;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@SpringBootApplication
public class NolanPersonalWebSIteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NolanPersonalWebSIteApplication.class, args);
        startUdpServer();
    }

    public static void startUdpServer() {
        // 定义服务器端口
        int serverPort = 5168;

        try {
            // 创建 DatagramSocket 对象
            DatagramSocket udpSocket = new DatagramSocket(serverPort);

            // 创建缓冲区用于接收数据包
            byte[] buffer = new byte[1024];

            System.out.println("UDP Server started. Listening on port " + serverPort);

            // 循环接收数据包
            while (true) {
                // 创建 DatagramPacket 对象来接收数据包
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // 接收数据包
                udpSocket.receive(packet);

                // 解析接收到的字节数组为 receivedInfo 对象
                CommDataInfo receivedInfo = new CommDataInfo(packet);

                // 处理接收到的 receivedInfo 对象
                System.out.println("Received data from " + receivedInfo.GetIPAddress() + ":" + receivedInfo.GetPort());
                System.out.println("Data length: " + receivedInfo.dataLen);
                String strJson;
                if ((receivedInfo.data[0] == 'D') && (receivedInfo.data[1] == 'D') && (receivedInfo.dataLen > 10)) {
                    int len = receivedInfo.dataLen - 10;
                    byte[] mask = new byte[8];

                    mask[0] = (byte) (receivedInfo.data[2] ^ 'L');
                    mask[1] = (byte) (receivedInfo.data[3] ^ 'S');
                    mask[2] = (byte) (receivedInfo.data[4] ^ 'Y');
                    mask[3] = (byte) (receivedInfo.data[5] ^ 0x65);
                    mask[4] = (byte) (receivedInfo.data[6] ^ 0xa7);
                    mask[5] = (byte) (receivedInfo.data[7] ^ 0xf1);
                    mask[6] = (byte) (receivedInfo.data[8] ^ 0x16);
                    mask[7] = (byte) (receivedInfo.data[9] ^ 0x58);

                    byte[] info = new byte[len];
                    for (int i = 0, j = 0; i < len; i++) {
                        info[i] = (byte) (receivedInfo.data[i + 10] ^ mask[j++]);
                        if (j > 7)
                            j = 0;
                    }
                    strJson = new String(info, 0, info.length);
                } else
                    strJson = new String(receivedInfo.data, 0, receivedInfo.dataLen);

                System.out.println("Data content: " + strJson);
                // 清空缓冲区
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
