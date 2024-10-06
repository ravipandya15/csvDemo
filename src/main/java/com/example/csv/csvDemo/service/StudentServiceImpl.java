package com.example.csv.csvDemo.service;

import com.example.csv.csvDemo.entity.Student;
import com.example.csv.csvDemo.repository.StudentRepository;
import com.example.csv.csvDemo.util.CSVUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Student> studentList = CSVUtility.csvToStudentList(file.getInputStream());
            studentRepository.saveAll(studentList);
        } catch (IOException e) {
            System.out.println("Data is not stored successfully: " + e.getCause());
        }
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }
}
