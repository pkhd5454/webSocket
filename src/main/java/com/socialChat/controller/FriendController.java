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
import com.socialChat.dto.MemberDTO;
import com.socialChat.persistence.FriendRepository;
import com.socialChat.persistence.MemberRepository;

@RequestMapping("/socialChat")
@RestController
public class FriendController {
	@Autowired
	MemberRepository repo;
	
	@Autowired
	FriendRepository frepo;
	
	@Secured("ROLE_USER")
	@GetMapping("/userName/{myId}")
	public ResponseEntity<List<String>> getFriend(@PathVariable("myId") String myId) {
		List<String> friends = frepo.getFriend(myId);
		return new ResponseEntity<List<String>>(friends, HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/userName/{myId}/{userName}")
	public ResponseEntity<List<MemberDTO>> isUser(@PathVariable("myId") String myId, @PathVariable("userName") String userName) {
		List<MemberDTO> userList = repo.getUserByUserName(myId, userName);
		return new ResponseEntity<List<MemberDTO>>(userList, HttpStatus.OK);
	}
	
	
	@Secured("ROLE_USER")
	@Transactional
	@PostMapping("/friend/{user}/{friend}")
	public ResponseEntity<Void> addFriend(@PathVariable("user") String user, @PathVariable("friend") String friend) {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser(user);
		pk.setFriend(friend);	
		
		if(frepo.existsById(pk))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		FriendShip friendShip = new FriendShip();
		friendShip.setFriendPK(pk);
		frepo.save(friendShip);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@Transactional
	@DeleteMapping("/friend/{user}/{friend}")
	public ResponseEntity<List<String>> deleteFriend(@PathVariable("user") String user, @PathVariable("friend") String friend) {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser(user);
		pk.setFriend(friend);
		frepo.deleteById(pk);
		
		FriendShipPK pk2 = new FriendShipPK();
		pk2.setUser(friend);
		pk2.setFriend(user);
		frepo.deleteById(pk2);
		
		List<String> friends = frepo.getFriend(user);
		return new ResponseEntity<List<String>>(friends, HttpStatus.OK);
	}
	
}
