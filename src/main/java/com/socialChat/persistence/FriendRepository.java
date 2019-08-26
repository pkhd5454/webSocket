package com.socialChat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.socialChat.dto.FriendShip;
import com.socialChat.dto.FriendShipPK;

public interface FriendRepository extends CrudRepository<FriendShip, FriendShipPK> {
	@Query("SELECT f1.friendPK.friend "
			+ "FROM FriendShip f1 inner join FriendShip f2 "
			+ "ON f1.friendPK.user = f2.friendPK.friend AND f1.friendPK.friend = f2.friendPK.user "
			+ "WHERE f1.friendPK.user=:userId")
	public List<String> getFriend(@Param("userId") String userId);
	
	
	@Query("SELECT f.friendPK.user FROM FriendShip f WHERE f.friendPK.friend NOT IN( "
			+ "select f1.friendPK.friend from FriendShip f1 inner join FriendShip f2 "
			+ "on f1.friendPK.user = f2.friendPK.friend and f1.friendPK.friend = f2.friendPK.user) "
			+ "AND f.friendPK.friend=:friendId")
	public List<String> getFriendRequest(@Param("friendId") String friendId);
	
	
}
