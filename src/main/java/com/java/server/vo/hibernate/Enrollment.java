package com.java.server.vo.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "discipline_id")
    private Integer discipline_id;

    @Column(name = "student_id")
    private Integer student_id;

    @Column(name = "grade")
    private Integer grade;
}
