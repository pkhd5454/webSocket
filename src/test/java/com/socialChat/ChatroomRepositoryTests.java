package com.socialChat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialChat.dto.ChatRoom;
import com.socialChat.persistence.ChatRoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class ChatroomRepositoryTests {
	@Autowired
	ChatRoomRepository repo;
	
	@Test
	public void findAll() {
		List<ChatRoom> list = (List<ChatRoom>) repo.findAll();
		list.forEach(item -> System.out.println(item));
	}
}
