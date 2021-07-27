package com.socialChat.dao.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 채팅방 */
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "chatroom")
public class Chatroom {
	public static final Chatroom NONE = new Chatroom();

	// 채팅방 고유 번호
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "roomId")
	private String roomId;

	// 채팅방 이름
	@Setter
	@Column(name = "roomName")
	private String roomName;

	// 방 개설자
	@Setter
	@Column(name = "roomOwner")
	private String roomOwner;

	// 입장인원수?
	@Setter
	@Column(name = "count")
	private long count;
	
	public static Chatroom create(String name, String owner) {
		Chatroom chatRoom = new Chatroom();
		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.roomName = name;
		chatRoom.roomOwner = owner;
		chatRoom.count = 0;
		return chatRoom;	
	}
}
