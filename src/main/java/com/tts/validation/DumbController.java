package com.tts.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DumbController {

	@GetMapping(path="/example")
	ResponseEntity<Map<String, String>> example() {	
		Map<String, String> values = new HashMap<>();
		values.put("name", "Scott");
		values.put("age", "19");
		values.put("occupation", "Software Engineer");
		
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity(values, HttpStatus.CREATED);
		return responseEntity;			
	}
}
