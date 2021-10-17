package com.clone.postmanc.users;

import java.util.Objects;
import com.clone.postmanc.users.role.RoleRepository;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.utils.Helpers;
import com.clone.postmanc.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private Helpers helpers;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) userRepository.findByUsername(username);
  }

  public Message addUser(SignupRequest signupRequest) {
    Message verification = helpers.verifyUser(signupRequest);
    System.out.println(verification == null);
    if (!Objects.nonNull(verification)) {
      if (!Objects.nonNull(signupRequest.getName())) {
        signupRequest.setName(helpers.getRondomString());
      }
      signupRequest.setRole(roleRepository.findByAuthority(AppConstants.USER));
      signupRequest.setPassword(encoder.encode(signupRequest.getPassword()));
      User user = new User(signupRequest);
      userRepository.save(user);
      return new Message(AppConstants.SIGNED_UP);
    }
    return verification;
  }

  public Message deactivateUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    if (Objects.nonNull(user)) {
      user.setEnabled(false);
      userRepository.save(user);
      return new Message(AppConstants.ACCOUNT_DEACTIVATED);
    }
    return new Message(AppConstants.ACCOUNT_NOT_FOUND);
  }
}
