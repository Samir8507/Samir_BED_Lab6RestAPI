package com.samir.studentmgmt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samir.studentmgmt.entity.Student;
import com.samir.studentmgmt.repository.StudentRepository;

@Repository
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		List <Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student findByid(int Id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(Id).get();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);

	}

	@Override
	public void deleteById(int Id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(Id);

	}

}

