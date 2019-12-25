package com.app.devops.service;

import com.app.devops.bean.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-test.xml")
public class StudentServiceITCase {

    @Autowired
    private StudentService service;

    @Test
    public void testSave() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@email.com");
        student.setDepartment("Computer Science");
        Student result = service.save(student);

        Assert.assertTrue(result.getId() != null && result.getCreationDate() != null);
    }

    @Test
    public void testDelete() {
        Optional<Student> result = service.findById(122L);
        service.delete(result.get());

        Assert.assertFalse(service.findById(122L).isPresent());
    }

    @Test
    public void testListAll() {
        Assert.assertFalse(service.listAll().isEmpty());
    }

    @Test
    public void testFindById() {
        Optional<Student> result = service.findById(121L);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(121L, result.get().getId().longValue());
    }

    @Test
    public void testFindByIdWhenIsEmpty() {
        Optional<Student> result = service.findById(101L);

        Assert.assertFalse(result.isPresent());
    }
}
