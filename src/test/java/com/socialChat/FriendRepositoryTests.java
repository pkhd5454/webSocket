package com.socialChat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialChat.dto.FriendShipPK;
import com.socialChat.persistence.FriendRepository;
import com.socialChat.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class FriendRepositoryTests {
	@Autowired
	FriendRepository repo;
	
	@Autowired
	MemberRepository mrepo;
	/*@Test
	public void testInsert() {
		FriendShip f = new FriendShip();
		FriendShipPK pk = new FriendShipPK();
		pk.setUser("friend2");
		pk.setFriend("admin");
		f.setFriend(pk);
		repo.save(f);
	}*/
	
	/*@Test
	public void testSearch() {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser("admin");
		System.out.println("--------------------------------------------------------------");
		repo.getFriend(pk.getUser()).forEach(f -> System.out.println(f));
		System.out.println("--------------------------------------------------------------");
	}*/
	
	/*@Test
	public void testSearch2() {
		System.out.println("--------------------------------------------------------------");
		mrepo.getUserByUserName("123").forEach(u -> System.out.println(u));
		System.out.println("--------------------------------------------------------------");
	}*/
	
	/*@Test
	public void test11() {
		FriendShipPK pk = new FriendShipPK();
		pk.setUser("usr");
		pk.setFriend("frd");
		System.out.println("-------------------------");
		System.out.println(pk.toString());
		System.out.println("-------------------------");
		FriendShipPK pkd = new FriendShipPK();
		pkd.setUser("usr");
		pkd.setFriend("frd");
		System.out.println("-------------------------");
		System.out.println(pkd.toString());
		System.out.println("-------------------------");
	}*/
	
	@Test
	public void testGetFriendRequest() {
		System.out.println("----------------------------------------------");
		repo.getFriendRequest("test12").forEach(item -> System.out.println(item));
		System.out.println("----------------------------------------------");
		
	}
}
