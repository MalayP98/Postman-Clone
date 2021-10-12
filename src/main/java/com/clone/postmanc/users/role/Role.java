package com.clone.postmanc.users.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String authority;

  public Role() {
  }

  public Role(String authority) {
    this.authority = authority;
  }

  public long getId() {
    return this.id;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}
