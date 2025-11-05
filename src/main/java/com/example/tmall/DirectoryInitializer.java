package com.example.tmall;

import com.example.tmall.util.ImageUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;

// 프로젝트에서 필요한 폴더들을 작성하는 설정 파일
@Configuration
public class DirectoryInitializer {

  @PostConstruct
  public void makeFolder() {
    File uploadFolder = new File(ImageUtil.UPLOAD_FOLDER);
    File tempFolder = new File(ImageUtil.TEMP_FOLDER);
    File profileFolder = new File(ImageUtil.PROFILE_FOLDER);
    File productFolder = new File(ImageUtil.PRODUCT_FOLDER);
    if(!uploadFolder.exists())
      uploadFolder.mkdir();
    if(!tempFolder.exists())
      tempFolder.mkdir();
    if(!profileFolder.exists())
      profileFolder.mkdir();
    if(!productFolder.exists())
      productFolder.mkdir();
  }
}
