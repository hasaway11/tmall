package com.example.tmall.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageUtil {
  public static final String DEFAULT_PROFILE = "default.webp";
  public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "upload";
  public static final String TEMP_FOLDER = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "temp" + File.separator;
  public static final String PROFILE_FOLDER = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "profile" + File.separator;
  public static final String PRODUCT_FOLDER = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "product" + File.separator;

  public static ResponseEntity<byte[]> readProfile(String fileName, String folderName) {
    try {
      File file = new File(folderName, fileName);
      byte[] imageBytes = Files.readAllBytes(file.toPath());
      MediaType mediaType = ImageUtil.getMediaType(fileName);
      return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }

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

  public static List<String> savaProductImage(List<MultipartFile> images) {
    if(images==null || images.isEmpty())
      return null;
    List<String> imageNames = new ArrayList<>();
    for(MultipartFile image:images) {
      String ext = FilenameUtils.getExtension(image.getOriginalFilename());
      String savedName = UUID.randomUUID().toString() + "." + ext;
      try {
        File target = new File(PRODUCT_FOLDER, savedName);
        image.transferTo(target);
        imageNames.add(savedName);
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    return imageNames;
  }
}
