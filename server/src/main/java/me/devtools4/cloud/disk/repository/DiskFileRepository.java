package me.devtools4.cloud.disk.repository;

import java.util.Optional;
import me.devtools4.cloud.disk.model.DiskFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskFileRepository extends JpaRepository<DiskFile, Long> {
  Optional<DiskFile> findDistinctByContentId(final Long fileId);
}