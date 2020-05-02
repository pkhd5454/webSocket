package com.socialChat.dao.repository;

import com.socialChat.dao.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRoleRepository
    extends JpaRepository<MemberRole, Long>, JpaSpecificationExecutor<MemberRole> {}
