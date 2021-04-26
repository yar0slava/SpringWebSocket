package com.java.server.controller;

import com.java.server.dao.EnrollmentDAO;
import com.java.server.dao.StudentDAO;
import com.java.server.dao.impl.EnrollmentDAOImpl;
import com.java.server.dao.impl.StudentDAOImpl;
import com.java.server.dto.EnrollmentDTO;
import com.java.server.dto.StudentDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommonController {

    private final StudentDAO studentDAO;
    private final EnrollmentDAO enrollmentDAO;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public CommonController(StudentDAO studentDAO, EnrollmentDAO enrollmentDAO, SimpMessagingTemplate simpMessagingTemplate) {
        this.studentDAO = studentDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/students")
    @SendTo("/data/students")
    public List<StudentDTO> getAllStudents(){
        return studentDAO.getAllStudents();
    }

    @MessageMapping("/enrollments")
    @SendTo("/data/enrollments")
    public List<EnrollmentDTO> getAllStudentById(int id) {
        List<EnrollmentDTO> allEnrolments = enrollmentDAO.getAllEnrolments(id);
        return allEnrolments;
    }
}

