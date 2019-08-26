package com.socialChat.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socialChat.dto.MemberDTO;
import com.socialChat.persistence.MemberRepository;

import groovy.util.logging.Log;

@Log
@Controller
@RequestMapping("/socialChat")

public class MemberController {
	@Autowired
	MemberRepository repo;
	
	@GetMapping("/signUp")
	public void signUp() {

	}

	@PostMapping("/signUp")
	@Transactional
	@Commit
	public String signUpPost(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {
		repo.save(member);
		rttr.addFlashAttribute("member", member);	
		return "redirect:/socialChat/signUpResult";
	}
	
	@GetMapping("/signUpResult")
	public void signUpResult() {
		
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/myPage")
	public void myPage(@RequestParam("userId") String id, Model model) {
		MemberDTO member = repo.findById(id).get();
		model.addAttribute("member", member);
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/myPage/modify")
	public String myPageModify(@ModelAttribute MemberDTO member) {
		MemberDTO tempMember = repo.findById(member.getId()).get();
		tempMember.setEmail(member.getEmail());
		tempMember.setPhoneNumber(member.getPhoneNumber());
		tempMember.setNickName(member.getNickName());
		repo.save(tempMember);
		return "redirect:/socialChat/home";
	}
}
