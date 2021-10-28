package com.clone.postmanc.utils;

import java.util.UUID;
import com.clone.postmanc.users.SignupRequest;
import com.clone.postmanc.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Helpers {

  @Autowired
  private UserRepository userRepository;

  private static PasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String encode(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  public String getRondomString() {
    return UUID.randomUUID().toString().replace(AppConstants.HYPHEN, AppConstants.EMPTY_STRING)
        .substring(0, 5);
  }

  public Message verifyUser(SignupRequest signupRequest) {
    if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
      return new Message(AppConstants.PASSWORD_NOT_MATCHED);
    }
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return new Message(AppConstants.USERNAME_ALREADY_EXIST);
    }
    return null;
  }
}
