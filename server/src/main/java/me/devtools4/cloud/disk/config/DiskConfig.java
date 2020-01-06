package me.devtools4.cloud.disk.config;

import javax.persistence.EntityManager;
import me.devtools4.cloud.disk.repository.DiskFileRepository;
import me.devtools4.cloud.disk.repository.FileContentCrudRepository;
import me.devtools4.cloud.disk.repository.FileContentRepository;
import me.devtools4.cloud.disk.repository.IdGenerator;
import me.devtools4.cloud.disk.repository.impl.DbIdGenerator;
import me.devtools4.cloud.disk.service.DiskService;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class DiskConfig {

  @Bean
  public DiskService diskService(FileContentRepository contentRepository,
      FileContentCrudRepository contentCrudRepository,
      DiskFileRepository fileRepository)
  {
    return new DiskService(contentRepository, contentCrudRepository, fileRepository);
  }

  @Bean
  public FileContentRepository fileContentRepository(JdbcTemplate jdbcTemplate, IdGenerator idGenerator) {
    return new FileContentRepository(jdbcTemplate, idGenerator);
  }

  @Bean
  public IdGenerator idGenerator(EntityManager em) {
    return new DbIdGenerator(em, "SELECT NEXT VALUE FOR FILE_ID_SEQ");
  }
}