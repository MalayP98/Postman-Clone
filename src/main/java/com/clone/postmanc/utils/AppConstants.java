package com.clone.postmanc.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.clone.postmanc.users.role.Role;

public final class AppConstants {

  private AppConstants() {
  }

  // Role Constants
  public final static String USER = "USER";
  public final static String ADMIN = "ADMIN";
  public final static List<Role> ROLES =
      Collections.unmodifiableList(Arrays.asList(new Role(USER), new Role(ADMIN)));
  public final static String[] ALL_ROLES = {USER, ADMIN};

  // URL
  public final static String API_URL = "/api";
  public final static String USER_URL = "/user";
  public final static String ADD_URL = "/add";
  public final static String SIGNUP_URL = "/signup";
  public final static String DEACTIVATE_URL = "/deactivate";

  // date format
  public final static String DATE_FORMAT = "dd/MM/yyyy";

  // Characters
  public final static String AT = "@";
  public final static String PERIOD = ".";
  public final static String HYPHEN = "-";
  public final static String EMPTY_STRING = "";

  // list of URLs that can be accessed by anyone.
  public final static String[] PUBLIC_URLS = {API_URL + USER_URL + SIGNUP_URL};

  // general and error messages
  public final static String PASSWORD_NOT_MATCHED = "Password not matched.";
  public final static String USERNAME_ALREADY_EXIST = "Username already exist. Try to log in.";
  public final static String SIGNED_UP = "Sign up successfull.";
  public final static String ACCOUNT_NOT_FOUND = "Account not found.";
  public final static String ACCOUNT_DEACTIVATED = "Account deactivated.";
}
