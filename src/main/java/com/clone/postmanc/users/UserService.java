package com.clone.postmanc.users;

import java.util.Objects;
import com.clone.postmanc.users.role.RoleRepository;
import com.clone.postmanc.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) userRepository.findByUsername(username);
  }

  public UserDTO addUser(UserDTO userDTO) {
    if (!Objects.nonNull(userDTO.getRole())) {
      userDTO.setRole(roleRepository.findByAuthority(AppConstants.USER));
    }
    if (isValid(userDTO)) {
      User user = new User(userDTO);
      userRepository.save(user);
    }
    return null;
  }

  private boolean isValid(UserDTO userDTO) {
    if (Objects.nonNull(userRepository.findByUsername(userDTO.getUsername()))) {
      return false;
    }
    String password = userDTO.getPassword();
    if (password.length() <= 5) {
      return false;
    }
    return true;
  }
}
