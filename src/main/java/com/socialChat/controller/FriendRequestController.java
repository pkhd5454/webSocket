package com.socialChat.controller;

import com.socialChat.dao.accessor.FriendshipDao;
import com.socialChat.dao.entity.Friendship;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/socialChat")
@Secured("ROLE_USER")
@RestController
public class FriendRequestController {
  @Autowired FriendshipDao friendshipDao;

  @GetMapping("/friendRequest/{userId}")
  public ResponseEntity<List<String>> getFriendRequest(@PathVariable("userId") String userId) {
    List<String> friendRequest = friendshipDao.getFriendRequest(userId);
    return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
  }

  @Transactional
  @PostMapping("/friendRequest/{userId}/{friendId}")
  public ResponseEntity<List<String>> acceptFriendRequest(
      @PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
    Friendship friendShip = new Friendship(userId, friendId);
    friendshipDao.save(friendShip);

    List<String> friendRequest = friendshipDao.getFriendRequest(userId);
    return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
  }

  @Transactional
  @DeleteMapping("/friendRequest/{userId}/{friendId}")
  public ResponseEntity<List<String>> denyFriendRequest(
      @PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
    friendshipDao.deleteById(userId, friendId);

    List<String> friendRequest = friendshipDao.getFriendRequest(userId);
    return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
  }
}
