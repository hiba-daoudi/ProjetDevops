package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import com.example.exception.ResourceNotFoundException;
import com.example.model.employee;
import com.example.repository.employeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private employeeRepository empRepo;

//create get all employees api
	@GetMapping("/employees")
	public List<employee> getAllEmployees() {
		return empRepo.findAll();
	}

//create employee
	@PostMapping("/employees")
	public employee createEmployee(@Valid @RequestBody employee emp) {
		return empRepo.save(emp);
	}

//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<employee> getEmployeeById(@PathVariable(value = "id") long employeeId)
			throws ResourceNotFoundException {
		employee empl = empRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found for this id : :" + employeeId));
		return ResponseEntity.ok().body(empl);

	}

//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<employee> UpdateEmployee(@PathVariable(value = "id") long employeeId,
			@RequestBody employee emp) throws ResourceNotFoundException {
		employee empl = empRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found for this id : :" + employeeId));
		empl.setfName(emp.getfName());
		empl.setlName(emp.getlName());
		empl.setEmail(emp.getEmail());
		empRepo.save(empl);
		return ResponseEntity.ok().body(empl);
	}

//delete employee by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
		empRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found for this id : :" + employeeId));
		empRepo.deleteById(employeeId);
		return ResponseEntity.ok().build();
	}
}
