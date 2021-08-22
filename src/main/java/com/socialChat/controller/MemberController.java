package com.socialChat.controller;

import com.socialChat.dao.accessor.MemberDao;
import com.socialChat.dao.entity.Member;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/socialChat")
@RequiredArgsConstructor
public class MemberController {
  private final MemberDao memberDao;

  @GetMapping("/signUp")
  public void signUp() {}

  @PostMapping("/signUp")
  @Transactional
  public String signUpPost(@ModelAttribute Member member, RedirectAttributes rttr) {
    memberDao.save(member);
    rttr.addFlashAttribute("member", member);
    return "redirect:/socialChat/signUpResult";
  }

  @GetMapping("/signUpResult")
  public void signUpResult() {}

  @Secured("ROLE_USER")
  @PostMapping("/myPage")
  public void myPage(@RequestParam("userId") String id, Model model) {
    Member member = memberDao.findById(id);
    model.addAttribute("member", member);
  }

  @Secured("ROLE_USER")
  @PostMapping("/myPage/modify")
  public String myPageModify(@ModelAttribute Member member) {
    Member tempMember = memberDao.findById(member.getId());
    tempMember.setEmail(member.getEmail());
    tempMember.setPhoneNumber(member.getPhoneNumber());
    tempMember.setNickName(member.getNickName());
    memberDao.save(tempMember);
    return "redirect:/socialChat/home";
  }
}
