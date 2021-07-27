package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.Friendship;
import com.socialChat.dao.entity.FriendshipId;
import com.socialChat.dao.repository.FriendshipRepository;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class FriendshipDao extends QuerydslRepositorySupport {
  private final FriendshipRepository repository;

  public FriendshipDao(FriendshipRepository repository) {
    super(Friendship.class);
    this.repository = repository;
  }

  public void save(Friendship friendship) {
    repository.save(friendship);
  }

  public List<Friendship> findAll() {
    return repository.findAll();
  }

  public Friendship findById(FriendshipId id) {
    return repository.findById(id).orElse(Friendship.NONE);
  }

  public Friendship findById(String member, String friend) {
    return findById(new FriendshipId(member, friend));
  }

  public void deleteById(FriendshipId id) {
    repository.deleteById(id);
  }

  public void deleteById(String member, String friend) {
    deleteById(new FriendshipId(member, friend));
  }

  public List<String> getFriend(String myId) {
    return null;
  }

  public List<String> getFriendRequest(String myId) {
    return null;
  }
}
