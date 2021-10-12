package com.clone.postmanc;

import com.clone.postmanc.users.role.Role;
import com.clone.postmanc.users.role.RoleRepository;
import com.clone.postmanc.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunOnStartup implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  public void saveDefaultRoles() {
    for (Role role : AppConstants.ROLES) {
      if (!roleRepository.existsByAuthority(role.getAuthority())) {
        roleRepository.save(role);
      }
    }
  }

  @Override
  public void run(String... args) throws Exception {
    saveDefaultRoles();
  }
}
