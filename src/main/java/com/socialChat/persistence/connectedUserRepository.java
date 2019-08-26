package com.socialChat.persistence;

import org.springframework.data.repository.CrudRepository;

import com.socialChat.dto.ConnectedUser;

public interface connectedUserRepository extends CrudRepository<ConnectedUser, String> {

}
