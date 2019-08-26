package com.socialChat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialChat.persistence.MemberRepository;

@Service
public class CustomUsersService implements UserDetailsService {

	@Autowired
	MemberRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username).filter(member -> member != null).map(member -> new CustomUser(member)).get();
	}

}
