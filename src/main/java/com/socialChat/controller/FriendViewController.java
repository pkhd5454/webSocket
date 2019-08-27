package com.socialChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialChat.dto.MemberDTO;
import com.socialChat.persistence.FriendRepository;
import com.socialChat.persistence.MemberRepository;
import com.socialChat.security.CustomUser;

@RequestMapping("/socialChat")
@Secured("ROLE_USER")
@Controller
public class FriendViewController {
	@Autowired
	FriendRepository repo;
	
	@Autowired
	MemberRepository mrepo;
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@GetMapping("/friend")
	public void friend() {
		
	}
	
	@PostMapping("/friendInfo")
	public void friendInfo(@RequestParam("friendId") String friendId, Model model) {
		MemberDTO friend = mrepo.findById(friendId).get();
		String status ="notExist";
		for(Object user : sessionRegistry.getAllPrincipals()) {	
			CustomUser customUser = (CustomUser) user;
			String userId = customUser.getMember().getId();
			if(userId.equals(friendId))
				status = "Exist";
		}
		model.addAttribute("friend", friend);
		model.addAttribute("status", status);
	}
}
