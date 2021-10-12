package com.clone.postmanc.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.clone.postmanc.users.role.Role;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @NonNull
  private String username;

  @NonNull
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  private Role role;

  private boolean accountNonExpired;

  private boolean accountNonLocked;

  private boolean credentialsNonExpired;

  private boolean enabled;

  public User() {

  }

  public User(UserDTO userDTO) {
    this.name = userDTO.getName();
    this.username = userDTO.getUsername();
    this.password = userDTO.getPassword();
    this.role = userDTO.getRole();
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<Role> roles = new ArrayList<>();
    roles.add(this.role);
    return roles;
  }
}
