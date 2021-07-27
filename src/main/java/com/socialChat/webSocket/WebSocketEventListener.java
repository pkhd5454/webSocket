package com.socialChat.webSocket;

import com.socialChat.dao.accessor.ChatroomDao;
import com.socialChat.dao.entity.Chatroom;
import com.socialChat.dao.entity.ConnectedUser;
import com.socialChat.dao.repository.ConnectedUserRepository;
import com.socialChat.webSocket.ChatMessage.MessageType;
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

@Component
public class WebSocketEventListener {
  @Autowired ConnectedUserRepository connectedUserRepository;

  @Autowired ChatroomDao chatroomDao;

  @Autowired private SimpMessageSendingOperations messagingTemplate;

  @EventListener
  @Transactional
  public void connectEvent(SessionConnectedEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    String sessionId = accessor.getSessionId();
    GenericMessage connectHeader =
        (GenericMessage) accessor.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
    Map<String, List<String>> nativeHeaders =
        (Map<String, List<String>>)
            connectHeader.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

    String roomId = nativeHeaders.get("roomId").get(0);
    ConnectedUser connectedUser = new ConnectedUser(sessionId);
    connectedUserRepository.save(connectedUser);

    Chatroom chatroom = chatroomDao.findById(roomId);
    chatroom.setCount(chatroom.getCount() + 1);
    chatroomDao.save(chatroom);
  }

  @EventListener
  @Transactional
  public void disconnectEvent(SessionDisconnectEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    String sessionId = accessor.getSessionId();
    String sender = accessor.getUser().getName();
    ConnectedUser connectedUser = connectedUserRepository.findById(sessionId).get();
    String roomId = connectedUser.getRoomId();
    connectedUserRepository.deleteById(sessionId);

    Chatroom chatroom = chatroomDao.findById(roomId);
    chatroom.setCount(chatroom.getCount() - 1);
    chatroomDao.save(chatroom);

    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setType(MessageType.LEAVE);
    chatMessage.setSender(sender);
    messagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chatMessage);
  }
}
