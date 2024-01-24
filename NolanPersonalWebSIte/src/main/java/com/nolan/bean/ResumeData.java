package com.nolan.bean;

import lombok.Data;

import java.util.List;

@Data
public class ResumeData {
    private String name;
    private String gender;
    private String age;
    private String phone;
    private String position;
    private String salary;
    private String email;
    private List<Experience> workExperience;
    private List<ProjectExperience> projectExperience;
    private List<Education> education;

    @Override
    public String toString() {
        return "ResumeData{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", email='" + email + '\'' +
                ", workExperience=" + workExperience +
                ", projectExperience=" + projectExperience +
                ", education=" + education +
                '}';
    }
}