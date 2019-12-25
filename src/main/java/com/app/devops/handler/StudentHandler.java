package com.app.devops.handler;

import com.app.devops.bean.Student;
import com.app.devops.service.StudentService;
import com.app.devops.util.SpringContextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.app.devops.handler.StudentHandler.MANAGED_BEAN_NAME;

@ViewScoped
@ManagedBean(name = MANAGED_BEAN_NAME)
public class StudentHandler implements Serializable {

    private static final Logger log = LogManager.getLogger(StudentHandler.class);
    static final String MANAGED_BEAN_NAME = "studentHandler";

    @Autowired
    private StudentService service;

    private List<Student> students;
    private Student student;

    @PostConstruct
    public void init() {
        this.loadService();
        this.loadStudents();
    }

    private void loadService() {
        Optional<StudentService> optService = SpringContextHelper.getBean(StudentService.class, StudentService.SERVICE_NAME);
        if(optService.isPresent()) {
            this.service = optService.get();
        } else {
            log.error("Failed to load service", StudentService.SERVICE_NAME);
            return;
        }
    }

    private void loadStudents() {
        this.students = service.listAll();
    }

    public void onCreate() {
        student = new Student();
    }

    public void onUpdate(Student student) {
        this.student = student;
    }
    public void onSave() {
        this.service.save(student);
        this.loadStudents();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Bienvenue à l'étudiant " + student.getFullName()));
        this.student = null;
    }

    public void onCancel() {
        this.student = null;
    }

    public void onDelete(Student student) {
        this.service.delete(student);
        this.loadStudents();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("L'etudiant " + student.getFullName() + " a été supprimé avec succès "));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
