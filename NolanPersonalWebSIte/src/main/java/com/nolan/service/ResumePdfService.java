package com.nolan.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nolan.bean.Education;
import com.nolan.bean.Experience;
import com.nolan.bean.ProjectExperience;
import com.nolan.bean.ResumeData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResumePdfService {
    private PdfPCell cell;
    // 标题字体，大小为18
    BaseFont baseFont;

    {
        try {
            baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 设置字体大小
    Font titleFont = new Font(baseFont, 18, Font.BOLD);
    Font chapterFont = new Font(baseFont, 14, Font.BOLD);
    Font normalFont = new Font(baseFont, 12);

    public byte[] generatePdf(ResumeData resumeData) throws IOException, DocumentException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            System.out.println(resumeData.toString());
            document.open();
            /*
             * 设置边框
             * */
            setBoarder(document);
            // 创建标题
            setTitle(document, resumeData);
            //三个段落内容
            set1Para(document, resumeData);
            set2Para(document, resumeData);
            //set3Para(document, resumeData);
            //设置工作经历
            setWorkExperience(document, resumeData.getWorkExperience());
            //项目经历
            setProjectExperience(document, resumeData.getProjectExperience());
            /*
             * 教育经历
             * */
            setEducation(document, resumeData.getEducation());
            document.close();
            return outputStream.toByteArray();
        }
    }

    public void setWorkExperience(Document document, List<Experience> workExperience) throws DocumentException {
        // Write work experience
        Paragraph paragraph = new Paragraph("工作经历", chapterFont);
        paragraph.setSpacingBefore(10);
        document.add(paragraph);
        for (Experience experience : workExperience) {
            document.add(new Paragraph("公司: " + experience.getCompany(), normalFont));
            document.add(new Paragraph("职位: " + experience.getPosition(), normalFont));
            document.add(new Paragraph("描述: " + experience.getDescription(), normalFont));
            document.add(new Paragraph("起止时间: " + experience.getStartDate() + " - " + experience.getEndDate(), normalFont));
        }
    }

    public PdfPCell setBoarder(Document document) {
        // 创建一个带有边框的表格，包含一个单元格
        PdfPTable tableWithBorder = new PdfPTable(1);
        tableWithBorder.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("nolan", normalFont));
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        tableWithBorder.addCell(cell);
        try {
            document.add(tableWithBorder);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return cell;
    }

    public void setProjectExperience(Document document, List<ProjectExperience> projectExperience) throws DocumentException {
        // Write work experience
        Paragraph paragraph = new Paragraph("项目经历", chapterFont);
        paragraph.setSpacingBefore(10);
        document.add(paragraph);
        for (ProjectExperience experience : projectExperience) {
            document.add(new Paragraph("项目名称: " + experience.getProjectName(), normalFont));
            document.add(new Paragraph("描述: " + experience.getDescription(), normalFont));
        }
    }

    public void setEducation(Document document, List<Education> education) throws DocumentException {
        // Write work experience
        Paragraph paragraph = new Paragraph("教育经历", chapterFont);
        paragraph.setSpacingBefore(10);
        document.add(paragraph);
        for (Education experience : education) {
            document.add(new Paragraph("学校: " + experience.getSchool(), normalFont));
            document.add(new Paragraph("专业: " + experience.getMajor(), normalFont));
            document.add(new Paragraph("学历: " + experience.getDegree(), normalFont));
            document.add(new Paragraph("起止时间: " + experience.getStartDate() + " - " + experience.getEndDate(), normalFont));
        }
    }

    public void setTitle(Document document, ResumeData resumeData) throws DocumentException {
        // 设置标题居中
        // 创建标题
        Paragraph title = new Paragraph(resumeData.getPosition() + resumeData.getName(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);
    }

    public void set1Para(Document document, ResumeData resumeData) throws DocumentException {
        document.add(new Paragraph("个人信息", chapterFont));
        Paragraph paragraphNameAndGender = new Paragraph();
        Chunk chunk1 = new Chunk("姓名:" + resumeData.getName(), normalFont);
        Chunk chunk2 = new Chunk("性别:" + resumeData.getGender(), normalFont);
        Chunk chunk3 = new Chunk("年龄:" + resumeData.getAge(), normalFont);
        paragraphNameAndGender.add(chunk1);
        paragraphNameAndGender.add("  ");
        paragraphNameAndGender.add(chunk2);
        paragraphNameAndGender.add("  ");
        paragraphNameAndGender.add(chunk3);
        document.add(paragraphNameAndGender);
    }

    public void set2Para(Document document, ResumeData resumeData) throws DocumentException {
        Paragraph paragraphPositionAndSalary = new Paragraph();
        Chunk chunk1 = new Chunk("岗位:" + resumeData.getPosition(), normalFont);
        Chunk chunk2 = new Chunk("预期薪资:" + resumeData.getSalary(), normalFont);
        Chunk chunk3 = new Chunk("邮箱:" + resumeData.getEmail(), normalFont);
        paragraphPositionAndSalary.add(chunk1);
        paragraphPositionAndSalary.add("  ");
        paragraphPositionAndSalary.add(chunk2);
        paragraphPositionAndSalary.add("  ");
        paragraphPositionAndSalary.add(chunk3);
        document.add(paragraphPositionAndSalary);
    }

    public void set3Para(Document document, ResumeData resumeData) throws DocumentException {
        Paragraph paragraphAgeAndEmail = new Paragraph();
        Chunk chunk1 = new Chunk("年龄:" + resumeData.getAge(), normalFont);
        Chunk chunk2 = new Chunk("邮箱:" + resumeData.getEmail(), normalFont);
        paragraphAgeAndEmail.add(chunk1);
        paragraphAgeAndEmail.add("  ");
        paragraphAgeAndEmail.add(chunk2);
        document.add(paragraphAgeAndEmail);
    }

    private void parseEducation(List<Education> educations, String pdfText, String sectionTitle) {
        parseEducation(educations, pdfText, sectionTitle);
    }

    private void parseExperience(List<Experience> experiences, String pdfText, String sectionTitle) {
        // Extract text between section title and next section title
        String sectionText = pdfText.substring(pdfText.indexOf(sectionTitle) + sectionTitle.length());
        int nextSectionIndex = sectionText.indexOf("工作经历:");
        if (nextSectionIndex == -1) {
            nextSectionIndex = sectionText.length();
        }
        sectionText = sectionText.substring(0, nextSectionIndex);

        // Split text into individual experiences
        String[] experienceTexts = sectionText.split("公司:");

        for (String experienceText : experienceTexts) {
            if (!experienceText.trim().isEmpty()) {
                Experience experience = new Experience();
                experience.setCompany(getValueFromText(experienceText, "公司:"));
                experience.setPosition(getValueFromText(experienceText, "职位:"));
                experience.setDescription(getValueFromText(experienceText, "描述:"));
                experience.setStartDate(getValueFromText(experienceText, "起止时间:").split(" - ")[0]);
                experience.setEndDate(getValueFromText(experienceText, "起止时间:").split(" - ")[1]);
                experiences.add(experience);
            }
        }
    }

    private String getValueFromText(String text, String label) {
        Pattern pattern = Pattern.compile(label + "\\s*([^\\n]*)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }
}
