package com.clone.postmanc.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.clone.postmanc.validate.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

  // has a upper-case character
  private final String UPPER_CASE = "(?=.*[A-Z]).*";

  // has a lower-case character
  private final String LOWER_CASE = "(?=.*[a-z]).*";

  // has a special character
  private final String SPECIAL_CHARACTERS = "(?=.*[@#\\$%\\^&\\*\\.!]).*";

  // cannot include character like [, ], {, }, ".
  private final String EXCLUDED_CHARACTERS = "(?!.*[\\[\\]\\(\\)\"']).*";

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    int c = 0;
    if (password.matches(UPPER_CASE)) {
      c++;
    }
    if (password.matches(LOWER_CASE)) {
      c++;
    }
    if (password.matches(SPECIAL_CHARACTERS)) {
      c++;
    }
    // should follow atleast 2 of the above conditions, should be between 5-20 and cannot include
    // any excluded characters.
    return (c >= 2 && password.matches(EXCLUDED_CHARACTERS) && password.length() >= 5
        && password.length() <= 20);
  }
}
