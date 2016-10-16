package me.wonwoo.file.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wonwoo on 2016. 10. 16..
 */

@ConfigurationProperties("storage")
public class StorageProperties {

  private String location = "upload-dir";
  private String host = "http://localhost:8080";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }
}