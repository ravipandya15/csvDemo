package com.example.csv.csvDemo.util;

import com.example.csv.csvDemo.entity.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtility {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"ID", "Student Name", "Email", "Mobile No."};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Student> csvToStudentList(InputStream is) {
        List<Student> studentList = null;
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            CSVParser csvParser = new CSVParser(bReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            studentList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Student student = new Student();
                student.setId(Integer.parseInt(csvRecord.get("ID")));
                student.setStudentName((csvRecord.get("Student Name")));
                student.setEmail((csvRecord.get("Email")));
                student.setMobileNo((csvRecord.get("mobile No.")));
                studentList.add(student);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException: " + e.getCause());
        } catch (IOException e) {
            System.out.println("CSV Data is failed to parse: " + e.getCause());
        }
        return studentList;
    }
}
