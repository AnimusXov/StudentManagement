package com.lh.student.model;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
@Setter @Getter @ToString
@NoArgsConstructor @RequiredArgsConstructor
public class Student{
@Id
@SequenceGenerator ( name = "student_sequence",
		sequenceName = "student_sequence",
		allocationSize = 1)
@GeneratedValue ( strategy = GenerationType.SEQUENCE,
		generator = "student_sequence")
private  Long id;
@NonNull
private String name;
@NonNull
private String surname;
@NonNull
private String email;
@Transient
@Getter(AccessLevel.NONE)
@Formula("date_part('year', (current_timestamp)) - date_part('year',dob)")
private Integer age;
@NonNull
private LocalDate dob;

public Integer getAge(){
	return Period.between(this.dob,LocalDate.now()).getYears();
}
}
