package com.socialChat.security;

import com.socialChat.dao.accessor.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUsersService implements UserDetailsService {
  private final MemberDao memberDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberDao.findByUserName(username).stream()
        .filter(member -> member != null)
        .map(member -> new CustomUser(member))
        .findFirst()
        .orElse(null);
  }
}
