package com.tts.validation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;
	
	@GetMapping(path="/employees")
	ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = repository.findAll();
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@PostMapping(path="/employees")
	ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee,
			                                BindingResult result) {
		if(repository.findByEmail(employee.getEmail()) != null) {
			result.rejectValue("email", "error.email", "Email already exists");
		}
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Employee savedEmployee = repository.save(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping(path="/employees/{id}")
	ResponseEntity<Void> updateEmployee(@Valid @RequestBody Employee employee,
			                            BindingResult result,
			                            @PathVariable("id") Long id) {
		if(employee.getId() != id) {
			result.rejectValue("id", "error.id", "id in body must match id in url");
		}
		Optional<Employee> employeeOptional = repository.findById(id);
		
		if (employeeOptional.isEmpty())
		{
			result.rejectValue("id", "error.id", "id was not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping(path="/employees/{id}") 
	ResponseEntity<Void> updateEmployee(@RequestBody Map<String, String> body,
										@PathVariable("id") Long id)
	{
		Optional<Employee> employeeOptional =repository.findById(id);
		if (employeeOptional.isEmpty())
		{			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Employee employee = employeeOptional.get();
		
		
		for(String key: body.keySet()) {
			switch(key) {
				case "firstName":
					employee.setFirstName(body.get(key));
					break;
				case "lastName":
					employee.setLastName(body.get(key));
					break;
				case "userName":
					employee.setUserName(body.get(key));
					break;
				default:
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		repository.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(path="/employees/{id}")
	ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		Optional<Employee> employeeOptional = repository.findById(id);
		if (employeeOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	
	
}
