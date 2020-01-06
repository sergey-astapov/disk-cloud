package me.devtools4.cloud.disk.service;

import static io.vavr.API.unchecked;

import com.google.common.collect.ImmutableMap;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import me.devtools4.cloud.disk.model.FileContent;
import me.devtools4.cloud.disk.model.DiskFile;
import me.devtools4.cloud.disk.repository.FileContentCrudRepository;
import me.devtools4.cloud.disk.repository.FileContentRepository;
import me.devtools4.cloud.disk.repository.DiskFileRepository;
import org.springframework.transaction.annotation.Transactional;

public class DiskService {

  private final FileContentRepository contentRepository;
  private final FileContentCrudRepository contentCrudRepository;
  private final DiskFileRepository fileRepository;

  public DiskService(FileContentRepository contentRepository,
      FileContentCrudRepository contentCrudRepository,
      DiskFileRepository fileRepository)
  {
    this.contentRepository = contentRepository;
    this.contentCrudRepository = contentCrudRepository;
    this.fileRepository = fileRepository;
  }

  @Transactional
  public Long saveFile(InputStream is, Map<String, String> attributes) {
    long contentId = contentRepository.save(is);
    fileRepository.save(DiskFile.builder()
        .contentId(contentId)
        .attributes(attributes)
        .build());
    return contentId;
  }

  @Transactional
  public Optional<InputStream> findFileConent(Long contentId) {
    return contentCrudRepository.findById(contentId)
        .map(FileContent::getContent)
        .map(unchecked(x -> x.getBinaryStream()));
  }

  @Transactional
  public Map<String, String> findFileAttributes(Long contentId) {
    return fileRepository.findDistinctByContentId(contentId)
        .map(DiskFile::getAttributes)
        .orElseGet(ImmutableMap::of);
  }
}