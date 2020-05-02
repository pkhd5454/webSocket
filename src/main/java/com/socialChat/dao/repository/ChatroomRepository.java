package com.socialChat.dao.repository;

import com.socialChat.dao.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChatroomRepository
    extends JpaRepository<Chatroom, String>, JpaSpecificationExecutor<Chatroom> {}
