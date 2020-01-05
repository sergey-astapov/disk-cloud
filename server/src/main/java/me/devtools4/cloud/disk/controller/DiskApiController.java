package me.devtools4.cloud.disk.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import me.devtools4.aops.annotations.Fallback;
import me.devtools4.aops.annotations.Fallback.Type;
import me.devtools4.aops.annotations.TimerMetric;
import me.devtools4.aops.annotations.Trace;
import me.devtools4.cloud.disk.api.DiskApi;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DiskApiController implements DiskApi {

  @Override
  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(value = FILE_SAVE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public String save(
      @RequestHeader(name = HttpHeaders.CONTENT_TYPE) String contentType,
      @RequestHeader(name = HttpHeaders.CONTENT_DISPOSITION) String contentDisposition,
      HttpServletRequest request)
  {
    return null;
  }

  @Override
  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(path = FILE_READ,
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
      method = RequestMethod.GET)
  public ResponseEntity<Resource> read(@PathVariable(name = "id") String id) {
    return null;
  }

  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(path = FILE_SAVE + "/multi",
      produces = APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public String save(@RequestParam("file") final MultipartFile file) {
    try (InputStream is = file.getInputStream()) {
      String fileName = file.getOriginalFilename();
      return null;
    } catch (IOException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}