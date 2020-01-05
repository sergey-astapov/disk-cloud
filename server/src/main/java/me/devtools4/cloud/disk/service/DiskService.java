package me.devtools4.cloud.disk.service;

import static io.vavr.API.unchecked;

import java.io.InputStream;
import java.util.Optional;
import me.devtools4.cloud.disk.model.DiskFile;
import me.devtools4.cloud.disk.repository.DiskFileCrudRepository;
import me.devtools4.cloud.disk.repository.DiskFileRepository;
import org.springframework.transaction.annotation.Transactional;

public class DiskService {

  private final DiskFileRepository repository;
  private final DiskFileCrudRepository crudRepository;

  public DiskService(DiskFileRepository repository, DiskFileCrudRepository crudRepository) {
    this.repository = repository;
    this.crudRepository = crudRepository;
  }

  @Transactional
  public Long saveFile(InputStream is) {
    return repository.save(is);
  }

  @Transactional
  public Optional<InputStream> readFile(Long id) {
    return crudRepository.findById(id)
        .map(DiskFile::getFile)
        .map(unchecked(x -> x.getBinaryStream()));
  }
}