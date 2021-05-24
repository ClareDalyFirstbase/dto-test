package com.service;

import com.data.S3File;

import java.io.File;
import java.util.UUID;

public class FileService {
  /**
   * "Saves" the file
   * @param file the file to save
   * @return the saved file
   */
  public S3File saveFile(final File file) {
    return new S3File(UUID.randomUUID(), file.getName(), ".jpg");
  }
}
