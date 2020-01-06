package me.devtools4.cloud.disk.repository;

import static java.sql.Statement.NO_GENERATED_KEYS;

import java.io.InputStream;
import java.sql.PreparedStatement;
import me.devtools4.aops.annotations.Trace;
import org.springframework.jdbc.core.JdbcTemplate;

public class FileContentRepository {

  private static final String sql = "insert into FILE_CONTENTS (ENTITY_ID, CONTENT) values (?, ?)";

  private final JdbcTemplate jdbcTemplate;
  private final IdGenerator idGenerator;

  public FileContentRepository(JdbcTemplate jdbcTemplate, IdGenerator idGenerator) {
    this.jdbcTemplate = jdbcTemplate;
    this.idGenerator = idGenerator;
  }

  @Trace
  public long save(InputStream is) {
    Long id = idGenerator.nextId();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, NO_GENERATED_KEYS);
      ps.setLong(1, id);
      ps.setBlob(2, is);
      return ps;
    });
    return id;
  }
}