package com.socialChat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.socialChat.dto.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String> {
	@Query("SELECT m FROM MemberDTO m WHERE m.name=:name and m.id !=:myId")
	public List<MemberDTO> getUserByUserName(@Param("myId") String myId, @Param("name") String name);
}
