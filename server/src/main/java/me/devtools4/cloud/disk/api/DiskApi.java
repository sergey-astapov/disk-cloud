package me.devtools4.cloud.disk.api;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DiskApi {
  String FILE_SAVE = "/api/file";
  String FILE_READ = "/api/file/{id}";
  String FILE_ATTRS_READ = "/api/file/{id}/attributes";

  Long save(String contentType, String contentDisposition, HttpServletRequest request);

  ResponseEntity<Resource> read(Long id);

  Map<String, String> attributes(Long id);
}