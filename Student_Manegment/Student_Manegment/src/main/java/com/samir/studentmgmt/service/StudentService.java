package com.samir.studentmgmt.service;

import java.util.List;

import com.samir.studentmgmt.entity.Student;

public interface StudentService {
	
	public List <Student> findAll();
	
	public Student findByid(int Id);
	
	public void save(Student student);
	
	public void deleteById(int Id);

}
