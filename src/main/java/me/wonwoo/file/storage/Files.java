package me.wonwoo.file.storage;

/**
 * Created by wonwoo on 2016. 10. 16..
 */
public class Files {

  private String name;
  private String path;

  public Files(String name, String path) {
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
