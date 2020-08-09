package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
	
	@Autowired
	private JPARepo jpaRepo;

	@GetMapping("/greet")
	public String greet(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/addemployee")
	public String addEmployee(@RequestParam(name="name") String name, 
			@RequestParam(name="salary") Integer salary,
			Model model) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSalary(salary);
		Employee employee2 = jpaRepo.save(employee);
		model.addAttribute("employee",employee2);
		return "employee";
	}
	
	@GetMapping("/getemployee")
	public String getEmployee(@RequestParam(name="id") Long id, Model model) {
		//Optional<Employee> employee = jpaRepo.findById(id);
		//return employee.get();
		Employee employee2 = jpaRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not avaialable : "+id));
		model.addAttribute("employee", employee2);
		return "employee";
		
	}
	
}
