package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.Student;
import com.demo.repository.Studentrepository;

@Component
public class StudentService 
{
	@Autowired
	private Studentrepository studentrepository;
	
	//get all students
	public List<Student> getAllStudents()
	{
		List<Student> list = studentrepository.findAll();
		return list;
	}
	
	//get student by id
	public Student getById(int id)
	{
		Student s = null;
		try {
			s=studentrepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//insert new student
	public Student saveStudent(Student student) 
	{
		return studentrepository.save(student);
	}
	
	//update student by id
	public void updateStudent(Student student, int id)
	{
		student.setId(id);
		studentrepository.save(student);
	}
	
	//delete student by id
	public void deleteByid(int id)
	{
		studentrepository.deleteById(id);
	}
}
