package me.devtools4.cloud.disk.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import me.devtools4.cloud.disk.DiskApiApp;
import javax.management.MBeanServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class MetricsConfig {
  @ConditionalOnProperty(prefix = "spring.jmx", name = "enabled", havingValue = "true")
  @Bean(initMethod = "start", destroyMethod = "stop")
  public JmxReporter jmxReporter(MBeanServer server, MetricRegistry metricRegistry) {
    return JmxReporter.forRegistry(metricRegistry)
        .inDomain(DiskApiApp.class.getPackage().getName() + ".metrics")
        .registerWith(server)
        .build();
  }

  @Bean
  public MetricRegistry metricRegistry() {
    return new MetricRegistry();
  }
}