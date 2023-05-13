package com.samir.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samir.studentmgmt.entity.Student;
import com.samir.studentmgmt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	public String getStudents(Model model) {
		List <Student> students = studentService.findAll();
		model.addAttribute("Students", students);
		return "students";
	}
	
	@RequestMapping("/addStudent")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "add-student";
	}
	
	@RequestMapping("/updateStudent")
	public String updateStudent(@RequestParam("studentId") int Id, Model model) {
		Student student = studentService.findByid(Id);
		model.addAttribute("Student", student);
		return "add-student";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("studentid") int studentid,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student student;
		if(studentid !=0)
		{
			student = studentService.findByid(studentid);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}
		else {
			student = new Student(firstName, lastName, course, country);
		}
		studentService.save(student);
		return "redirect:/student/list";
	}
	
	@DeleteMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int studentId) {
		studentService.deleteById(studentId);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("mymsg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("mymsg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;
	}

}
