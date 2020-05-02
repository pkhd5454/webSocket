package com.socialChat.dao.accessor;

import com.socialChat.dao.entity.MemberRole;
import com.socialChat.dao.repository.MemberRoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRoleDao {
  private final MemberRoleRepository repository;

  public void save(MemberRole role) {
    repository.save(role);
  }

  public List<MemberRole> findAll() {
    return repository.findAll();
  }

  public MemberRole findById(Long id) {
    return repository.findById(id).orElse(MemberRole.NONE);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
