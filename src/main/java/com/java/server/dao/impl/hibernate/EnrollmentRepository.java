package com.java.server.dao.impl.hibernate;

import com.java.server.dao.EnrollmentDAO;
import com.java.server.dto.EnrollmentDTO;
import com.java.server.vo.hibernate.Discipline;
import com.java.server.vo.hibernate.Enrollment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EnrollmentRepository implements EnrollmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<EnrollmentDTO> getAllEnrolments(int studentId) {
        System.out.println("HIBERNATE enrollments");

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Enrollment> query = currentSession.createQuery("from Enrollment where student_id=:id", Enrollment.class).setParameter("id",studentId);

        return query.getResultList().stream()
                .map(e -> {
                    Discipline d = getDiscipline(e.getDiscipline_id());
                    return new EnrollmentDTO(e.getStudent_id(), e.getId(), e.getDiscipline_id(),
                            e.getGrade(), d.getCredits(), d.getName());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Discipline getDiscipline(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Discipline.class, id);
    }
}
