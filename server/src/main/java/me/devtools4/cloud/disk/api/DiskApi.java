package me.devtools4.cloud.disk.api;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DiskApi {
  String FILE_SAVE = "/api/file";
  String FILE_READ = "/api/file/{id}";

  String save(String contentType, String contentDisposition, HttpServletRequest request);

  ResponseEntity<Resource> read(String docId);
}