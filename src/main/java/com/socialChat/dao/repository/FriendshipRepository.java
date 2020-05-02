package com.socialChat.dao.repository;

import com.socialChat.dao.entity.Friendship;
import com.socialChat.dao.entity.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendshipRepository
    extends JpaRepository<Friendship, FriendshipId>, JpaSpecificationExecutor<Friendship> {}
