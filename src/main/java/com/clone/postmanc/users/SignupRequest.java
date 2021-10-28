package com.clone.postmanc.users;

import com.clone.postmanc.users.role.Role;
import com.clone.postmanc.utils.Helpers;
import com.clone.postmanc.validate.annotations.Email;
import com.clone.postmanc.validate.annotations.Password;
import org.springframework.lang.NonNull;

public class SignupRequest {

  private String name;

  @NonNull
  @Email
  private String username;

  @NonNull
  @Password
  private String confirmPassword;

  @NonNull
  @Password
  private String password;

  private Role role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = Helpers.encode(password);
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = Helpers.encode(confirmPassword);
  }
}
