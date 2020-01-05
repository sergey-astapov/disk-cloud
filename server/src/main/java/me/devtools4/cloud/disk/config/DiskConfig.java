package me.devtools4.cloud.disk.config;

import javax.persistence.EntityManager;
import me.devtools4.cloud.disk.repository.DiskFileCrudRepository;
import me.devtools4.cloud.disk.repository.DiskFileRepository;
import me.devtools4.cloud.disk.repository.IdGenerator;
import me.devtools4.cloud.disk.repository.impl.DbIdGenerator;
import me.devtools4.cloud.disk.service.DiskService;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class DiskConfig {

  @Bean
  public DiskService diskService(DiskFileRepository repository,
      DiskFileCrudRepository crudRepository) {
    return new DiskService(repository, crudRepository);
  }

  @Bean
  public DiskFileRepository diskFileRepository(JdbcTemplate jdbcTemplate, IdGenerator idGenerator) {
    return new DiskFileRepository(jdbcTemplate, idGenerator);
  }

  @Bean
  public IdGenerator idGenerator(EntityManager em) {
    return new DbIdGenerator(em, "SELECT NEXT VALUE FOR FILE_ID_SEQ");
  }
}