package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.Member;
import com.socialChat.dao.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {
  private final MemberRepository repository;

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
}
