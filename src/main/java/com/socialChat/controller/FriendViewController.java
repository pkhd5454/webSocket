package com.socialChat.controller;

import com.socialChat.dao.accessor.FriendshipDao;
import com.socialChat.dao.accessor.MemberDao;
import com.socialChat.dao.entity.Member;
import com.socialChat.security.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/socialChat")
@Secured("ROLE_USER")
@Controller
@RequiredArgsConstructor
public class FriendViewController {
  private final FriendshipDao friendshipDao;
  private final MemberDao memberDao;
  private final SessionRegistry sessionRegistry;

  @GetMapping("/friend")
  public void friend() {}

  @PostMapping("/friendInfo")
  public void friendInfo(@RequestParam("friendId") String friendId, Model model) {
    Member friend = memberDao.findById(friendId);
    String status = "notExist";
    for (Object user : sessionRegistry.getAllPrincipals()) {
      CustomUser customUser = (CustomUser) user;
      String userId = customUser.getMember().getId();
      if (userId.equals(friendId)) status = "Exist";
    }
    model.addAttribute("friend", friend);
    model.addAttribute("status", status);
  }
}
