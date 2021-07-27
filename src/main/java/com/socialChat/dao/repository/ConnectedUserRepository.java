package com.socialChat.dao.repository;

import com.socialChat.dao.entity.ConnectedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedUserRepository extends JpaRepository<ConnectedUser, String> {}
