package com.socialChat.controller;

import com.socialChat.dao.accessor.FriendshipDao;
import com.socialChat.dao.accessor.MemberDao;
import com.socialChat.dao.entity.Friendship;
import com.socialChat.dao.entity.Member;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FriendController {
  private final MemberDao memberDao;
  private final FriendshipDao friendshipDao;

  @GetMapping("/userName/{myId}")
  public ResponseEntity<List<String>> getFriend(@PathVariable("myId") String myId) {
    List<String> friends = friendshipDao.getFriend(myId);
    return new ResponseEntity<>(friends, HttpStatus.OK);
  }

  @GetMapping("/userName/{myId}/{userName}")
  public ResponseEntity<List<Member>> isUser(
      @PathVariable("myId") String myId, @PathVariable("userName") String userName) {
    List<Member> userList = memberDao.getUserByUserName(myId, userName);
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @Transactional
  @PostMapping("/friend/{user}/{friend}")
  public ResponseEntity<Void> addFriend(
      @PathVariable("user") String user, @PathVariable("friend") String friend) {
    if (friendshipDao.findById(user, friend) != Friendship.NONE) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    Friendship friendship = new Friendship(user, friend);
    friendshipDao.save(friendship);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Transactional
  @DeleteMapping("/friend/{user}/{friend}")
  public ResponseEntity<List<String>> deleteFriend(
      @PathVariable("user") String user, @PathVariable("friend") String friend) {
    friendshipDao.deleteById(user, friend);
    friendshipDao.deleteById(friend, user);

    List<String> friends = friendshipDao.getFriend(user);
    return new ResponseEntity<>(friends, HttpStatus.OK);
  }
}
