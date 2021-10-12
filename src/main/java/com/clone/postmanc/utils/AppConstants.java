package com.clone.postmanc.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.clone.postmanc.users.role.Role;

public final class AppConstants {

  private AppConstants() {
  }

  // Role Constants
  public final static String USER = "ROLE_USER";
  public final static String ADMIN = "ROLE_ADMIN";
  public final static List<Role> ROLES =
      Collections.unmodifiableList(Arrays.asList(new Role(USER), new Role(ADMIN)));

  // URL
  public final static String API_URL = "/api";
  public final static String USER_URL = "/user";
  public final static String ADD_URL = "/add";

}
