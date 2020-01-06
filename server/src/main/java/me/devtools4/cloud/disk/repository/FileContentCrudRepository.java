package me.devtools4.cloud.disk.repository;

import me.devtools4.cloud.disk.model.FileContent;
import org.springframework.data.repository.CrudRepository;

public interface FileContentCrudRepository extends CrudRepository<FileContent, Long> {}