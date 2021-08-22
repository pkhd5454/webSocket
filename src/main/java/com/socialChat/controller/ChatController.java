package com.socialChat.controller;

import com.socialChat.webSocket.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/chat/sendMessage")
  public void sendMessage(@Payload ChatMessage chatMessage) {
    messagingTemplate.convertAndSend("/sub/chatroom/" + chatMessage.getRoomId(), chatMessage);
  }

  @MessageMapping("/chat/addUser")
  public void addUser(@Payload ChatMessage chatMessage) {
    messagingTemplate.convertAndSend("/sub/chatroom/" + chatMessage.getRoomId(), chatMessage);
  }
}
