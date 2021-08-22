package com.socialChat.controller;

import com.socialChat.dao.accessor.MemberDao;
import com.socialChat.dao.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/socialChat")
@RestController
@RequiredArgsConstructor
public class ValidController {
  private final MemberDao memberDao;

  @GetMapping("/validate/{userId}")
  public ResponseEntity<String> getValidation(@PathVariable("userId") String userId) {
    String validation = "";
    if (memberDao.findById(userId) != Member.NONE) validation = "no";
    else validation = "ok";
    return new ResponseEntity<String>(validation, HttpStatus.OK);
  }
}
