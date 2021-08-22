package com.socialChat.controller;

import com.socialChat.dao.accessor.ChatroomDao;
import com.socialChat.dao.entity.Chatroom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/socialChat")
@RequiredArgsConstructor
public class ChatRoomController {
  private final ChatroomDao chatroomDao;

  @GetMapping("/room")
  public String room(Model model) {
    model.addAttribute("roomList", chatroomDao.findAll());
    return "socialChat/room";
  }

  @PostMapping("/chatroom")
  public String entranceRoom(Model model, @RequestParam("roomId") String roomId) {
    model.addAttribute("room", chatroomDao.findById(roomId));
    return "socialChat/chatroom";
  }

  @PostMapping("/deleteChatRoom")
  public String deleteChatRoom(@RequestParam("roomId") String roomId) {
    chatroomDao.deleteById(roomId);
    return "redirect:/socialChat/home";
  }

  @GetMapping("/createRoom")
  public String createRoom() {
    return "socialChat/createRoom";
  }

  @PostMapping("/createRoom")
  public String postCreateRoom(
      @RequestParam("roomName") String roomName, @RequestParam("roomOwner") String roomOwner) {
    chatroomDao.save(Chatroom.create(roomName, roomOwner));
    return "redirect:/socialChat/room";
  }
}
