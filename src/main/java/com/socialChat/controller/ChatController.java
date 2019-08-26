package com.socialChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.socialChat.webSocket.ChatMessage;

@Controller
public class ChatController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/chat/sendMessage")	
	public void sendMessage(@Payload ChatMessage chatMessage) {
		messagingTemplate.convertAndSend("/sub/chatroom/" + chatMessage.getRoomId(), chatMessage);
	}
	
	@MessageMapping("/chat/addUser")
	public void addUser(@Payload ChatMessage chatMessage) {		
		messagingTemplate.convertAndSend("/sub/chatroom/" + chatMessage.getRoomId(), chatMessage);
	}
}
