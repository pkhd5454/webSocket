package com.socialChat.persistence;

import org.springframework.data.repository.CrudRepository;

import com.socialChat.dto.ChatRoom;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {
	
}
