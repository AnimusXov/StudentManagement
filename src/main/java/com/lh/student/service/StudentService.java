package com.lh.student.service;

import com.lh.student.model.Student;
import com.lh.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;




@Service
public class StudentService{
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService( StudentRepository studentRepository ){this.studentRepository = studentRepository;}
	
	public List<Student> getStudentList(){
		return studentRepository.findAll();
	}
	
	public void addNewStudent( Student student ){
		Optional<Student> studentByEmail =
				studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()){
			throw new IllegalStateException("E-mail taken");
		}
		studentRepository.save(student);
	}
	public Optional<Student> getStudent( Long studentId){
		return studentRepository.findById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId,String name, String email){
		Student student = studentRepository.findById(studentId).orElseThrow(
				() ->new IllegalStateException("Student with id " + studentId + " does not exist"));
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
			student.setName(name);
		}
		if (email!=null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()){
				throw new IllegalStateException("E-mail taken");
			}
			student.setEmail(email);
		}
	}


	public void deleteStudent( Long studentId ){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("Student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}

public void deleteStudents(){
		boolean exists = studentRepository.existsById(1L);
	if(!exists){
		throw new IllegalStateException("No students exist");
	}
	studentRepository.deleteAll();
	
}
}