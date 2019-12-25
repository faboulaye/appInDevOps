package com.app.devops.service;

import com.app.devops.bean.Student;
import com.app.devops.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class StudentServiceTest {
    @InjectMocks
    private StudentService service = new StudentServiceImp();

    @Mock
    private StudentRepository repository;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        Student student = Mockito.mock(Student.class);
        Mockito.doReturn(111L).when(student).getId();
        Mockito.doReturn(student).when(repository).save(Mockito.any(Student.class));

        Student result = service.save(student);
        Assert.assertEquals(new Long("111"), result.getId());
    }

    @Test
    public void testFindById() {
        Student student = Mockito.mock(Student.class);
        Mockito.doReturn("John").when(student).getFirstName();
        Mockito.doReturn(Optional.ofNullable(student)).when(repository).findById(Mockito.anyLong());

        Optional<Student> result = service.findById(111L);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("John", result.get().getFirstName());
    }

    @Test
    public void testFindByIdWhenEmpty() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.anyLong());

        Optional<Student> result = service.findById(111L);
        Assert.assertFalse(result.isPresent());
    }
}
