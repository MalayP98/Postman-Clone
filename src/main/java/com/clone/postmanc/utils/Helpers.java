package com.clone.postmanc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.clone.postmanc.users.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Helpers {

  public static String getRondomString() {
    return UUID.randomUUID().toString().replace(AppConstants.HYPHEN, AppConstants.EMPTY_STRING)
        .substring(0, 5);
  }

  public static User getPrincipal() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  // Reference to generics is not given as value type can be either String or Object.
  public static Map<String, Object> stringToMap(String str, String keyValueSeprator,
      String elementSeprator) {
    Map<String, Object> result = new HashMap<>();
    for (String element : str.split(elementSeprator)) {
      String[] pair = element.split(keyValueSeprator);
      result.put(pair[0], pair[1]);
    }
    return result;
  }

  public static void writeToFile(byte[] bytes, Path path) {
    try (OutputStream outputStream = Files.newOutputStream(path)) {
      outputStream.write(bytes);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static void cleanTempDirectory() throws IOException {
    FileUtils.cleanDirectory(new File(AppConstants.TEMP_FOLDER_LOC));
  }

  public static File getFile(MultipartFile multipartFile)
      throws IllegalStateException, IOException {
    File file = new File(AppConstants.TEMP_FOLDER_LOC + multipartFile.getOriginalFilename());
    file.createNewFile();
    multipartFile.getBytes();
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(multipartFile.getBytes());
    fos.close();
    return file;
  }
}
