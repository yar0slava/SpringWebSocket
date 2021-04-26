package com.java.server.dao;

import com.java.server.dto.StudentDTO;

import java.util.List;

public interface StudentDAO {

    List<StudentDTO> getAllStudents();

    void save(StudentDTO studentDTO);

    void delete(StudentDTO studentDTO);

    void update(StudentDTO studentDTO);
}

