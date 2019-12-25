package com.app.devops.service;

import com.app.devops.bean.Student;
import com.app.devops.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.app.devops.service.StudentService.SERVICE_NAME;

@Service(SERVICE_NAME)
public class StudentServiceImp implements StudentService {

    private static final Logger log = LogManager.getLogger(StudentServiceImp.class);

    @Autowired
    private StudentRepository repository;

    @PostConstruct
    public void init() {
        log.info("Start Student service");
    }

    @Override
    @Transactional
    public Student save(Student Student) {
        return repository.save(Student);
    }

    @Override
    @Transactional
    public void delete(Student student) {
        if(student != null) {
            repository.delete(student);
        } else {
            log.error("Student object is required");
        }
    }

    @Override
    public List<Student> listAll() {
        return (List<Student>) repository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }


}
