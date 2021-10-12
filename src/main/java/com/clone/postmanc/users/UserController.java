package com.clone.postmanc.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api" + "/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/add")
  // TODO : Validate user.
  public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO));
  }
}
