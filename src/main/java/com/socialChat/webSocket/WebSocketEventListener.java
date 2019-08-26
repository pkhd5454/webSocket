package com.socialChat.webSocket;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.socialChat.dto.ChatRoom;
import com.socialChat.dto.ConnectedUser;
import com.socialChat.persistence.ChatRoomRepository;
import com.socialChat.persistence.connectedUserRepository;
import com.socialChat.webSocket.ChatMessage.MessageType;

@Component
public class WebSocketEventListener {
	@Autowired
	connectedUserRepository repo;
	
	@Autowired
	ChatRoomRepository crepo; 
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
	@Transactional
	public void connectEvent(SessionConnectedEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = accessor.getSessionId();
		GenericMessage connectHeader = (GenericMessage)accessor.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
	    Map<String, List<String>> nativeHeaders = (Map<String, List<String>>) connectHeader.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
	    
	    String roomId = nativeHeaders.get("roomId").get(0);
	    ConnectedUser connectedUser = new ConnectedUser();
	    connectedUser.setRoomId(roomId);
	    connectedUser.setSessionId(sessionId);
	    repo.save(connectedUser);
	    
	    ChatRoom chatroom = crepo.findById(roomId).get();
	    chatroom.setCount(chatroom.getCount()+1);
	    crepo.save(chatroom);
	}
	
	@EventListener
	@Transactional
	public void disconnectEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = accessor.getSessionId();
		String sender = accessor.getUser().getName();   	
		ConnectedUser connectedUser = repo.findById(sessionId).get();
		String roomId = connectedUser.getRoomId();
		repo.deleteById(sessionId);
		
		ChatRoom chatroom = crepo.findById(roomId).get();
		chatroom.setCount(chatroom.getCount()-1);
	    crepo.save(chatroom);
		
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setType(MessageType.LEAVE);
		chatMessage.setSender(sender);
		messagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chatMessage);
		
	}
}
