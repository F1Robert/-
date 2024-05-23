package com.nolan.controller;

import com.itextpdf.text.DocumentException;
import com.nolan.bean.ResumeData;
import com.nolan.service.ResumePdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ResumeController {
    @RequestMapping("/make_resume")
    public String index() {
        return "make_resume";
    }

    @RequestMapping("/make_resume_2")
    public String index2() {
        return "make_resume_2";
    }

    @PostMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf(@RequestBody ResumeData resumeData) throws IOException, DocumentException {
        ResumePdfService resumePdfService = new ResumePdfService();
        byte[] pdfBytes = resumePdfService.generatePdf(resumeData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(pdfBytes);

        // 修改保存路径
        String filePath = "src/main/resources/static/pdfs/resume.pdf";
        Files.write(Paths.get(filePath), pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "resume.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
