package com.example.csv.csvDemo.repository;

import com.example.csv.csvDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
