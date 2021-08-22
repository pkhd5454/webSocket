package com.socialChat.controller;

import com.socialChat.dao.accessor.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socialChat")
@RequiredArgsConstructor
public class mainController {
  private final MemberDao memberDao;

  @GetMapping("/home")
  public void home() {}

  @PostMapping("/home")
  public void home(Model model) {}

  @GetMapping("/project")
  public void project() {}
}
