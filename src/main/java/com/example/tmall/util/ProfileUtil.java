package com.example.tmall.util;

import org.springframework.http.*;

import java.io.*;

public class ProfileUtil {
  public static final String DEFAULT_PROFILE = "default.webp";
  public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "upload";
  public static final String TEMP_FOLDER = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "temp" + File.separator;
  public static final String PROFILE_FOLDER = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "profile" + File.separator;

  public static MediaType getMediaType(String imageName) {
    String mimeType = "image/jpeg";
    imageName = imageName.toLowerCase();
    if(imageName.endsWith(".png"))
      mimeType = "image/png";
    else if(imageName.endsWith(".gif"))
      mimeType = "image/gif";
    else if(imageName.endsWith(".webp"))
      mimeType = "image/webp";
    return MediaType.parseMediaType(mimeType);
  }
}
