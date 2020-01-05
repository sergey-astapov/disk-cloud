package me.devtools4.cloud.disk.repository;

import me.devtools4.cloud.disk.model.DiskFile;
import org.springframework.data.repository.CrudRepository;

public interface DiskFileCrudRepository extends CrudRepository<DiskFile, Long> {

}