package com.socialChat.security;

import com.socialChat.dao.accessor.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersService implements UserDetailsService {

  @Autowired MemberDao memberDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberDao.findByUserName(username).stream()
        .filter(member -> member != null)
        .map(member -> new CustomUser(member))
        .findFirst()
        .orElse(null);
  }
}
