package com.java.server.dao;

import com.java.server.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentDAO {

    List<EnrollmentDTO> getAllEnrolments(int studentId);

}
