package com.socialChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socialChat")
public class LoginController {
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public void loginPost() {
		
	}
	
	@GetMapping("/accessDenied") 
	public void accessDenied() {
	
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "redirect:/socialChat/home";
	}
}
