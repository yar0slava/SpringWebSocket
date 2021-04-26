package com.java.server.dao.impl.hibernate;

import com.java.server.dao.StudentDAO;
import com.java.server.dto.StudentDTO;
import com.java.server.vo.hibernate.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<StudentDTO> getAllStudents() {
        System.out.println("HIBERNATE students");

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> query = currentSession.createQuery("from Student", Student.class);
        return query.getResultList().stream()
                .map(s -> new StudentDTO(s.getId(),s.getName(),s.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(StudentDTO studentDTO) {
        System.out.println("HIBERNATE students");

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(new Student(studentDTO.getId(),studentDTO.getName(),studentDTO.getCourse()));
    }

    @Override
    @Transactional
    public void delete(StudentDTO studentDTO) {
        System.out.println("HIBERNATE students");

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("Delete from Student where id=:id");
        query.setParameter("id", studentDTO.getId());

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void update(StudentDTO studentDTO) {
        System.out.println("HIBERNATE students");

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery(
                "update Student " +
                        "set name=:name, course=:course " +
                        "where id=:id");
        query.setParameter("id", studentDTO.getId());
        query.setParameter("name", studentDTO.getName());
        query.setParameter("course", studentDTO.getCourse());

        query.executeUpdate();
    }
}
