package com.nolan.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageService {
    private String uploadPath = "/upload/";

    public String saveImage(MultipartFile file) throws IOException {
        // 获取静态资源目录的绝对路径
        String staticPath = ResourceUtils.getFile("classpath:static").getAbsolutePath();

        // 构建上传路径，将文件保存到 static/upload/ 目录下
        Path uploadDir = Paths.get(staticPath, "upload").toAbsolutePath().normalize();
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 构建目标文件路径
        Path targetPath = uploadDir.resolve(fileName).toAbsolutePath().normalize();

        // 复制文件
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // 返回文件的相对路径
        return "upload/" + fileName;
    }
}
