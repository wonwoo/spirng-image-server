package me.wonwoo.file.storage;

/**
 * Created by wonwoo on 2016. 10. 16..
 */
public class StorageException extends RuntimeException {

  public StorageException(String message) {
    super(message);
  }

  public StorageException(String message, Throwable cause) {
    super(message, cause);
  }
}