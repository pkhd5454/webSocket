package com.socialChat.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.socialChat.dto.MemberDTO;
import com.socialChat.dto.MemberRoleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "ROLE_";
	private MemberDTO member;
	
	public CustomUser(MemberDTO member) {
		super(member.getId(), "{noop}" + member.getPassword(), grantAuthority(member.getRoles()));
		this.member = member;
	}

	private static Collection<? extends GrantedAuthority> grantAuthority(List<MemberRoleDTO> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(PREFIX + role.getRole())));
		return list;
	}

}
