package com.samir.studentmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samir.studentmgmt.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
