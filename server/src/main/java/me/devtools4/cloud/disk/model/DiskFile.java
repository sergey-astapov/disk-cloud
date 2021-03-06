package me.devtools4.cloud.disk.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiskFile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ENTITY_ID")
  private Long id;

  @Column(name = "CONTENT_ID")
  private Long contentId;

  @ElementCollection(fetch = FetchType.EAGER)
  private Map<String, String> attributes = new HashMap<>();
}