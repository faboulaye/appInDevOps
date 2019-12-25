package com.app.devops.service;

import com.app.devops.bean.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    String SERVICE_NAME = "studentService";

    Student save(Student Student);

    void delete(Student student);

    List<Student> listAll();

    Optional<Student> findById(Long id);
}
