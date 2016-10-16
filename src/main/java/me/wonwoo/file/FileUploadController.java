package me.wonwoo.file;

import me.wonwoo.file.storage.Files;
import me.wonwoo.file.storage.StorageFileNotFoundException;
import me.wonwoo.file.storage.StorageProperties;
import me.wonwoo.file.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by wonwoo on 2016. 10. 16..
 */

@Controller
public class FileUploadController {

  private final StorageService storageService;
  private final StorageProperties storageProperties;

  @Autowired
  public FileUploadController(StorageService storageService, StorageProperties storageProperties) {
    this.storageService = storageService;
    this.storageProperties = storageProperties;
  }

  @GetMapping("/")
  public String listUploadedFiles(Model model) throws IOException {

    model.addAttribute("files", storageService
      .loadAll()
      .map(path ->
        new Files(
          path.getFileName().toString(),
          MvcUriComponentsBuilder
          .fromMethodName(UriComponentsBuilder.fromHttpUrl(storageProperties.getHost()), FileUploadController.class, "serveFile", path.getFileName().toString())
          .build().toString())
        )
      .collect(Collectors.toList()));
    model.addAttribute("host",storageProperties.getHost());
    return "uploadForm";
  }


  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
      .body(file);
  }

  @PostMapping("/")
  public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
    storageService.store(file);
    redirectAttributes.addFlashAttribute("message",
      "You successfully uploaded " + file.getOriginalFilename() + "!");

    return "uploadForm";
  }

  @GetMapping("/delete")
  public String deleteFile(@RequestParam("filename") String name) {
    storageService.delete(name);
    return "redirect:/";
  }

  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }
}
