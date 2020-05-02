package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.Chatroom;
import com.socialChat.dao.repository.ChatroomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatroomDao {
  private final ChatroomRepository repository;

  public void save(Chatroom chatroom) {
    repository.save(chatroom);
  }

  public List<Chatroom> findAll() {
    return repository.findAll();
  }

  public Chatroom findById(String id) {
    return repository.findById(id).orElse(Chatroom.NONE);
  }

  public void deleteById(String id) {
    repository.deleteById(id);
  }
}
