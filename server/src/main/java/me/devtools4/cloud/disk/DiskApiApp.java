package me.devtools4.cloud.disk;

import me.devtools4.cloud.disk.config.AspectsConfig;
import me.devtools4.cloud.disk.config.MetricsConfig;
import me.devtools4.cloud.disk.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({
    SwaggerConfig.class,
    AspectsConfig.class,
    MetricsConfig.class
})
public class DiskApiApp {
  public static void main(String[] args) {
    SpringApplication.run(DiskApiApp.class, args);
  }
}