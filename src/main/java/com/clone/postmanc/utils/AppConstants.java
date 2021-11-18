package com.clone.postmanc.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.clone.postmanc.users.role.Role;
import org.springframework.core.env.Environment;

public final class AppConstants {

  private AppConstants() {
  }

  // Secret
  public final static String SECRET = "AoFLSfi7hsW0aqv7";

  // Role Constants
  public final static String USER = "USER";
  public final static String ADMIN = "ADMIN";
  public final static List<Role> ROLES =
      Collections.unmodifiableList(Arrays.asList(new Role(USER), new Role(ADMIN)));
  public final static String[] ALL_ROLES = {USER, ADMIN};

  // URL
  public final static String API_URL = "/api";
  public final static String USER_URL = "/user";
  public final static String REQUEST = "/request";
  public final static String SEND = "/send";
  public final static String ADD_URL = "/add";
  public final static String SIGNUP_URL = "/signup";
  public final static String DEACTIVATE_URL = "/deactivate";

  // date format
  public final static String DATE_FORMAT = "dd/MM/yyyy";

  // Symbols
  public final static String AT = "@";
  public final static String EQUALS = "=";
  public final static String PERIOD = ".";
  public final static String HYPHEN = "-";
  public final static String EMPTY_STRING = "";
  public final static String COLON = ":";
  public static final String SPACE = " ";
  public static final String QUESTION_MARK = "?";
  public static final String AMPERSAND = "&";
  public static final String COMMA = ",";

  // list of URLs that can be accessed by anyone.
  public final static String[] PUBLIC_URLS = {API_URL + USER_URL + SIGNUP_URL};

  // general and error messages
  public final static String PASSWORD_NOT_MATCHED = "Password not matched.";
  public final static String USERNAME_ALREADY_EXIST = "Username already exist. Try to log in.";
  public final static String SIGNED_UP = "Sign up successfull.";
  public final static String ACCOUNT_NOT_FOUND = "Account not found.";
  public final static String ACCOUNT_DEACTIVATED = "Account deactivated.";

  // Misc
  public final static String THREAD_NAME = "postman-request-";

  // temp folder location
  public final static String TEMP_FOLDER_LOC = "/Users/malaypandey/Work/temp";
}
