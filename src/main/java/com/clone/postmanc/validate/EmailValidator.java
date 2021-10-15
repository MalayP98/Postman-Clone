package com.clone.postmanc.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.validate.annotations.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {

  // email cannot contain more that one period, @ and characters like [, ], ", {, }.
  private final String EMAIL_REGEX = "(?!.*(\\.{2}|@{2}|-{2}))(?!.*[\\[|\\]|\"|{|}]).*";

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (!email.matches(EMAIL_REGEX)) {
      return false;
    }
    String[] emailComponents = email.split(AppConstants.AT);
    String local = emailComponents[0];
    String domain = emailComponents[1];
    if (local.startsWith(AppConstants.PERIOD) || domain.startsWith(AppConstants.PERIOD)
        || local.endsWith(AppConstants.PERIOD) || domain.endsWith(AppConstants.PERIOD)) {
      return false;
    }
    return true;
  }
}
