package com.socialChat.dto;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of= {"roomId","roomName"})
@Table(name="chatroom")
@Entity
public class ChatRoom {
	@Id
	private String roomId;
	private String roomName;
	private String roomOwner;
	private long count;
	
	public static ChatRoom create(String name, String owner) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.roomName = name;
		chatRoom.roomOwner = owner;
		chatRoom.count = 0;
		return chatRoom;	
	}
}
