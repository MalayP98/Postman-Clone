package com.clone.postmanc.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
// @DependsOn("defaultPasswordEncoder")
public class Encoders {

  private static SimplePasswordEncoder simplePasswordEncoder;

  private static PasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public Encoders(PasswordEncoder defaultPasswordEncoder) throws UnsupportedEncodingException {
    Encoders.simplePasswordEncoder = new SimplePasswordEncoder();
    Encoders.bCryptPasswordEncoder = defaultPasswordEncoder;
  }

  /**
   * Decode the suppiled string if the string is encrypted by SimplePasswordEncoder. Usually used to
   * decrypt the incoming password while sing up process.
   */
  public static String decode(String encryptedString)
      throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    return simplePasswordEncoder.decrypt(encryptedString);
  }

  /**
   * Encrypt's the password before saving to Database.
   */
  public static String encode(String rawPassword) {
    return bCryptPasswordEncoder.encode(rawPassword);
  }
}
