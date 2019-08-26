package com.socialChat.controller;

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

import com.socialChat.dto.FriendShip;
import com.socialChat.dto.FriendShipPK;
import com.socialChat.persistence.FriendRepository;

@RequestMapping("/socialChat")
@RestController
public class FriendRequestController {
	@Autowired
	FriendRepository repo;
	
	@Secured("ROLE_USER")
	@GetMapping("/friendRequest/{userId}")
	public ResponseEntity<List<String>> getFriendRequest(@PathVariable("userId") String userId) {
		List<String> friendRequest = repo.getFriendRequest(userId);
		return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@Transactional
	@PostMapping("/friendRequest/{userId}/{friendId}")
	public ResponseEntity<List<String>> acceptFriendRequest(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser(userId);
		pk.setFriend(friendId);
		FriendShip friendShip = new FriendShip();
		friendShip.setFriendPK(pk);
		repo.save(friendShip);
		
		List<String> friendRequest = repo.getFriendRequest(userId);
		return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@Transactional
	@DeleteMapping("/friendRequest/{userId}/{friendId}")
	public ResponseEntity<List<String>> denyFriendRequest(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser(friendId);
		pk.setFriend(userId);
		repo.deleteById(pk);
		
		List<String> friendRequest = repo.getFriendRequest(userId);
		return new ResponseEntity<List<String>>(friendRequest, HttpStatus.OK);
	}
}
