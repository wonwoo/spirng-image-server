package me.wonwoo.file.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;
/**
 * Created by wonwoo on 2016. 10. 16..
 */
public interface StorageService {

  void init();

  void store(MultipartFile file);

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);

  void deleteAll();

  void delete(String filename);

}