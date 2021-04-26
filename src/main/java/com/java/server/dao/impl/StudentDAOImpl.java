package com.java.server.dao.impl;

import com.java.server.config.DatabaseConfig;
import com.java.server.dao.StudentDAO;
import com.java.server.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//@Service
public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = new LinkedList<>();

        String SELECT_QUERY = "SELECT * FROM students";

        try (Connection connection = DatabaseConfig.connection;
             final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final StudentDTO student = getStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("JDBC students");
        return students;
    }

    @Override
    public void save(StudentDTO studentDTO) {
        String INSERT_QUERY = "INSERT INTO students (id, name, course) " +
                "VALUES (?,?,?);";

        try (Connection connection = DatabaseConfig.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setLong(1, studentDTO.getId());
            preparedStatement.setString(2, studentDTO.getName());
            preparedStatement.setString(4, studentDTO.getCourse());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("JDBC students");
    }

    @Override
    public void delete(StudentDTO studentDTO) {
        String DELETE_QUERY = "DELETE FROM students WHERE id=?;";

        try (Connection connection = DatabaseConfig.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, studentDTO.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("JDBC students");
    }

    @Override
    public void update(StudentDTO studentDTO) {
        String UPDATE_QUERY = "UPDATE students " +
                " SET name=?, course=?" +
                " WHERE id=?;";

        try (Connection connection = DatabaseConfig.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(2, studentDTO.getName());
            preparedStatement.setString(4, studentDTO.getCourse());
            preparedStatement.setLong(1, studentDTO.getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("JDBC students");
    }

    public StudentDTO getStudent(ResultSet resultSet) throws SQLException {
        final int studentId = resultSet.getInt("id");
        final String studentName = resultSet.getString("name");
        final String studentCourse = resultSet.getString("course");
        return new StudentDTO(studentId, studentName, studentCourse);

    }
}
