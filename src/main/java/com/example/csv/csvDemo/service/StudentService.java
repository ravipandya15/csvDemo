package com.example.csv.csvDemo.service;

import com.example.csv.csvDemo.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    void save(MultipartFile file);
    List<Student> getStudentList();
}
