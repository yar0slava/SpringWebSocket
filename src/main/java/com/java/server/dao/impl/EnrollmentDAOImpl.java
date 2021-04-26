package com.java.server.dao.impl;

import com.java.server.config.DatabaseConfig;
import com.java.server.dao.EnrollmentDAO;
import com.java.server.dto.EnrollmentDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//@Service
public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public List<EnrollmentDTO> getAllEnrolments(int studentId) {
        List<EnrollmentDTO> enrollments = new LinkedList<>();
        try {
            final Connection connection = DatabaseConfig.connection;
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT e.id, e.grade, e.discipline_id, e.student_id, d.credits, d.name " +
                            "FROM enrollment e, discipline d " +
                            "where e.discipline_id = d.id and e.student_id = ?");
            preparedStatement.setLong(1, studentId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final EnrollmentDTO enrollment = getEnrollment(resultSet);
                enrollments.add(enrollment);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("JDBC enrollments");
        return enrollments;
    }

    private EnrollmentDTO getEnrollment(ResultSet resultSet) throws SQLException{

        final int student_id = resultSet.getInt("student_id");
        final int enrollment_id = resultSet.getInt("id");
        final int discipline_id = resultSet.getInt("discipline_id");
        final int grade = resultSet.getInt("grade");
        final int credits = resultSet.getInt("credits");
        final String discipline_name = resultSet.getString("name");

        return new EnrollmentDTO(student_id, enrollment_id, discipline_id, grade, credits, discipline_name);
    }

}