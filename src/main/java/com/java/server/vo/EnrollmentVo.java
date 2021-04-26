package com.java.server.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentVo {
    private Integer id;
    private Integer discipline_id;
    private Integer student_id;
    private Integer grade;
}
