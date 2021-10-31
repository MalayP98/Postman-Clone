package com.clone.postmanc.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import com.clone.postmanc.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class is used for decoding passwords comming from the cilent's side which are encrypted with
 * a key same as that present on the server. It is usually not used for encoding. For encoding
 * {@link BCryptPasswordEncoder} is used.
 */
public class SimplePasswordEncoder implements PasswordEncoder {

  private Logger LOG = LoggerFactory.getLogger(getClass());

  private final String AES = "AES";
  private final String UTF8 = "UTF-8";

  private SecretKeySpec key;

  public SimplePasswordEncoder() throws UnsupportedEncodingException {
    this.key = new SecretKeySpec(AppConstants.SECRET.getBytes(UTF8), AES);
  }

  public String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Cipher encryter = Cipher.getInstance(AES);
    encryter.init(Cipher.ENCRYPT_MODE, key);
    return Base64.getEncoder().encodeToString(encryter.doFinal(str.getBytes()));
  }

  public String decrypt(String encodedPassword)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    Cipher decrypter = Cipher.getInstance(AES);
    decrypter.init(Cipher.DECRYPT_MODE, this.key);
    return new String(decrypter.doFinal(Base64.getDecoder().decode(encodedPassword)));
  }

  @Override
  public String encode(CharSequence rawPassword) {
    String passowrd = rawPassword.toString();
    try {
      return encrypt(passowrd);
    } catch (Exception e) {
      LOG.error("Cannot encrypt password. Some error occured");
    }
    return passowrd;
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    String password = rawPassword.toString();
    try {
      password = encode(password);
      return (password.equals(encodedPassword));
    } catch (Exception e) {
      LOG.error("Cannot decrypt password.");
      return false;
    }
  }
}
