package com.socialChat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialChat.dto.ChatRoom;
import com.socialChat.persistence.ChatRoomRepository;

@Controller
@RequestMapping("/socialChat")
public class ChatRoomController {
	@Autowired
	ChatRoomRepository repo;
	
	@GetMapping("/room")
	public void room(Model model) {
		List<ChatRoom> roomList = (List<ChatRoom>) repo.findAll();
		model.addAttribute("roomList", roomList);
	}
	
	
	
	@PostMapping("/chatroom")
	public String entranceRoom(Model model, @RequestParam("roomId") String roomId) {
		ChatRoom room = repo.findById(roomId).get();
		model.addAttribute("room", room);
		return "socialChat/chatroom";
	}
	
	@PostMapping("/deleteChatRoom")
	public String deleteChatRoom(@RequestParam("roomId") String roomId) {
		repo.deleteById(roomId);
		return "redirect:/socialChat/home";
	}
	
	@GetMapping("/createRoom")
	public void createRoom() {
		
	}
	
	@PostMapping("/createRoom")
	public String postCreateRoom(@RequestParam("roomName") String roomName, @RequestParam("roomOwner") String roomOwner) {
		ChatRoom chatroom = ChatRoom.create(roomName, roomOwner);
		repo.save(chatroom);
		return "redirect:/socialChat/room";
	}
}
