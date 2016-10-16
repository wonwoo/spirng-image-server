package me.wonwoo.file.storage;

/**
 * Created by wonwoo on 2016. 10. 16..
 */
public class StorageFileNotFoundException extends StorageException {

  public StorageFileNotFoundException(String message) {
    super(message);
  }

  public StorageFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}