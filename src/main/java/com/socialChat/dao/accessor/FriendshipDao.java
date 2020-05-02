package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.Friendship;
import com.socialChat.dao.entity.FriendshipId;
import com.socialChat.dao.repository.FriendshipRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendshipDao {
  private final FriendshipRepository repository;

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
}
