package com.socialChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialChat.persistence.MemberRepository;

@RequestMapping("/socialChat")
@RestController
public class ValidController {
	@Autowired
	MemberRepository repo;
	
	@GetMapping("/validate/{userId}")
	public ResponseEntity<String> getValidation(@PathVariable("userId") String userId) {
		String validation = "";
		if(repo.existsById(userId))
			validation = "no";
		else
			validation = "ok";
		return new ResponseEntity<String>(validation, HttpStatus.OK);
		
	}
}
