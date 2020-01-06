package me.devtools4.cloud.disk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Blob;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "FILE_CONTENTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "content")
public class FileContent {

  @Id
  @Column(name = "ENTITY_ID")
  private Long id;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "CONTENT")
  @JsonIgnore
  public Blob content;
}