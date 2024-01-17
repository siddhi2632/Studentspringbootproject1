package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Student;

public interface Studentrepository extends JpaRepository<Student, Integer>
{
	public Student findById(int id);
}
