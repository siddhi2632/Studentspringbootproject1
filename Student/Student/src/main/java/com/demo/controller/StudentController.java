package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Student;
import com.demo.service.StudentService;

@RestController
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	//get all students
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents()
	{
		List<Student> list = studentService.getAllStudents();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	//get by id
	@GetMapping("/student{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") int id)
	{
		Student s = studentService.getById(id);
		if(s==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(s));
	}
	
	//insert new student
	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student s = null;
		try {
			s = studentService.saveStudent(student);
			System.out.println(s);
			return ResponseEntity.of(Optional.of(s));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//update by id
	@PutMapping("/student{id}")
	public ResponseEntity<Student> updateByid(@RequestBody Student student, @PathVariable("id") int id)
	{
		try {
			studentService.updateStudent(student, id);
			return ResponseEntity.ok().body(student);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//delete by id
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id)
	
	{
		try {
			studentService.deleteByid(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
