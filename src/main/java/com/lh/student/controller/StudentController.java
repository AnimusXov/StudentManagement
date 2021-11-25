package com.lh.student.controller;

import com.lh.student.model.Student;
import com.lh.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController{
private final StudentService studentService;

@Autowired
public StudentController( StudentService studentService ){
	this.studentService = studentService;}

@GetMapping
public List<Student> getStudentList(){
	return studentService.getStudentList();
}

@GetMapping(path = "{studentId}")
public Optional<Student> getStudent( @PathVariable("studentId") Long studentId){
	return studentService.getStudent(studentId);
}

@PostMapping()
public void registerNewStudent(@RequestBody Student student){
	studentService.addNewStudent(student);
}

@DeleteMapping(path = "{studentId}")
public void deleteStudent(@PathVariable("studentId") Long studentId){
	studentService.deleteStudent(studentId);
}
@DeleteMapping
public void deleteStudents(){
	studentService.deleteStudents();
}


@PutMapping(path = "{studentId}")
public void updateStudent( @PathVariable("studentId") Long studentId,
                          @RequestBody Student student ){
	studentService.updateStudent(studentId,student);
}

}
