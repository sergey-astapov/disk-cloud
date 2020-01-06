package me.devtools4.cloud.disk.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import me.devtools4.aops.annotations.Fallback;
import me.devtools4.aops.annotations.Fallback.Type;
import me.devtools4.aops.annotations.TimerMetric;
import me.devtools4.aops.annotations.Trace;
import me.devtools4.cloud.disk.api.DiskApi;
import me.devtools4.cloud.disk.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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

  private final DiskService diskService;

  @Autowired
  public DiskApiController(DiskService diskService) {
    this.diskService = diskService;
  }

  @Override
  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(value = FILE_SAVE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public Long save(
      @RequestHeader(name = HttpHeaders.CONTENT_TYPE) String contentType,
      @RequestHeader(name = HttpHeaders.CONTENT_DISPOSITION) String contentDisposition,
      HttpServletRequest request)
  {
    try (InputStream is = request.getInputStream()) {
      return diskService.saveFile(is, ImmutableMap.of(
          HttpHeaders.CONTENT_TYPE, contentType,
          HttpHeaders.CONTENT_DISPOSITION, contentDisposition
      ));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(path = FILE_READ,
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
      method = RequestMethod.GET)
  public ResponseEntity<Resource> read(@PathVariable(name = "id") Long id) {
    return diskService.findFileConent(id)
        .map(InputStreamResource::new)
        .map(x -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body((Resource)x))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(path = FILE_ATTRS_READ,
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public Map<String, String> attributes(@PathVariable(name = "id") Long id) {
    return diskService.findFileAttributes(id);
  }

  @Trace
  @Fallback(type = Type.Log)
  @TimerMetric
  @RequestMapping(path = FILE_SAVE + "/multi",
      produces = APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public Long save(@RequestParam("file") final MultipartFile file) {
    try (InputStream is = file.getInputStream()) {
      String fileName = file.getOriginalFilename();
      return diskService.saveFile(is, ImmutableMap.of(
          "fileName", file.getOriginalFilename(),
          "contentType", file.getContentType(),
          "size", String.valueOf(file.getSize())
      ));
    } catch (IOException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}