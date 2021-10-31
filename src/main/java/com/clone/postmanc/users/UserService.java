package com.clone.postmanc.users;

import java.util.Objects;
import com.clone.postmanc.users.exceptions.SignupException;
import com.clone.postmanc.users.role.RoleRepository;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.utils.Helpers;
import com.clone.postmanc.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

  private Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private Helpers helpers;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) userRepository.findByUsername(username);
  }

  @Transactional(rollbackFor = SignupException.class)
  public Message addUser(SignupRequest signupRequest) throws SignupException {
    Message verification = verifyUser(signupRequest);
    if (!Objects.nonNull(verification)) {
      if (!Objects.nonNull(signupRequest.getName())) {
        signupRequest.setName(helpers.getRondomString());
      }
      // TODO : create an environment variable with name DEFAULT_ROLE and use it here.
      signupRequest.setRole(roleRepository.findByAuthority(AppConstants.USER));
      User user = new User(signupRequest);
      user = userRepository.save(user);
      LOG.info("User add with id = " + user.getId() + " default role USER");
      return new Message(AppConstants.SIGNED_UP);
    }
    return verification;
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

  @Transactional
  public Message deactivateUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    if (Objects.nonNull(user)) {
      user.setEnabled(false);
      user = userRepository.save(user);
      LOG.info("User with id = {} disabled", user.getId());
      return new Message(AppConstants.ACCOUNT_DEACTIVATED);
    }
    return new Message(AppConstants.ACCOUNT_NOT_FOUND);
  }
}
