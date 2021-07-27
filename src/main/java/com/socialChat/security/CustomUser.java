package com.socialChat.security;

import com.socialChat.dao.entity.Member;
import com.socialChat.dao.entity.MemberRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {

  /** */
  private static final long serialVersionUID = 1L;

  private static final String PREFIX = "ROLE_";
  private Member member;

  public CustomUser(Member member) {
    super(member.getId(), "{noop}" + member.getPassword(), grantAuthority(member.getRoles()));
    this.member = member;
  }

  private static Collection<? extends GrantedAuthority> grantAuthority(List<MemberRole> roles) {
    List<GrantedAuthority> list = new ArrayList<>();
    roles.forEach(role -> list.add(new SimpleGrantedAuthority(PREFIX + role.getRole())));
    return list;
  }
}
