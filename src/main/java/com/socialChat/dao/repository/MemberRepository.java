package com.socialChat.dao.repository;

import com.socialChat.dao.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository
    extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member> {}
