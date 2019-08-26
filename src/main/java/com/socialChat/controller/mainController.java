package com.socialChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.socialChat.persistence.MemberRepository;


@Controller
@RequestMapping("/socialChat")
public class mainController {
	@Autowired
	MemberRepository repo;
	
	@GetMapping("/home")
	public void home() {
		
	}
	
	@PostMapping("/home")
	public void home(Model model) {
		
	}
	
	@GetMapping("/project")
	public void project() {
		
	}
	
	
}
