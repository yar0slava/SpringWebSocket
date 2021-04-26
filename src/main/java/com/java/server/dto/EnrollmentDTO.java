package com.java.server.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Integer student_id;
    private Integer id;
    private Integer discipline_id;
    private Integer grade;

    private int credits;
    private String discipline_name;
}
