package me.devtools4.cloud.disk.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.Optional;
import me.devtools4.cloud.disk.controller.DiskApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

  @Value("${spring.profiles.active:default}")
  private String activeProfile;

  @Autowired(required = false)
  private BuildProperties buildProperties;

  @Bean
  public Docket documentation() {
    String version = version();
    return new Docket(SWAGGER_2)
        .select()
        .apis(basePackage(DiskApiController.class.getPackage().getName()))
        .build()
        .apiInfo(new ApiInfoBuilder()
            .title("Disk API")
            .description("version=" + version + " profile=" + activeProfile)
            .version(version)
            .build());
  }

  private String version() {
    return Optional.ofNullable(buildProperties)
        .map(BuildProperties::getVersion)
        .orElse("SNAPSHOT");
  }
}