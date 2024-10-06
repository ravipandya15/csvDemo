package com.example.csv.csvDemo.controller;

import com.example.csv.csvDemo.entity.Student;
import com.example.csv.csvDemo.service.StudentService;
import com.example.csv.csvDemo.util.CSVUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/csv/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) {
        String message = "";
        if (CSVUtility.hasCSVFormat(file)) {
            try {
                studentService.save(file);
                message = "The file is uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception ex) {
                message = "The file is not uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("student-list")
    public ResponseEntity<?> getStudents() {
        List<Student> studentList = studentService.getStudentList();
        if (!studentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentList);
    }
}
