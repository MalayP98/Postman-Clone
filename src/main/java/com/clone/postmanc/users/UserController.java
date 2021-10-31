package com.clone.postmanc.users;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import com.clone.postmanc.users.exceptions.SignupException;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = AppConstants.API_URL + AppConstants.USER_URL)
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = AppConstants.SIGNUP_URL)
  public ResponseEntity<Message> signup(@Valid @RequestBody SignupRequest signupRequest)
      throws SignupException {
    return new ResponseEntity<>(userService.addUser(signupRequest), HttpStatus.CREATED);
  }

  @DeleteMapping(path = AppConstants.DEACTIVATE_URL)
  public ResponseEntity<Message> deactivate() {
    return new ResponseEntity<>(userService.deactivateUser(), HttpStatus.OK);
  }
}
