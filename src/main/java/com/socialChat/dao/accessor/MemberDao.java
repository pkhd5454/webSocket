package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.Member;
import com.socialChat.dao.repository.MemberRepository;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao extends QuerydslRepositorySupport {
  private final MemberRepository repository;

  public MemberDao(MemberRepository repository) {
    super(Member.class);
    this.repository = repository;
  }

  public void save(Member member) {
    repository.save(member);
  }

  public List<Member> findAll() {
    return repository.findAll();
  }

  public Member findById(String id) {
    return repository.findById(id).orElse(Member.NONE);
  }

  public void deleteById(String id) {
    repository.deleteById(id);
  }

  public List<Member> getUserByUserName(String myId, String userName) {
    return null;
  }

  public List<Member> findByUserName(String username) {
    return null;
  }
}
