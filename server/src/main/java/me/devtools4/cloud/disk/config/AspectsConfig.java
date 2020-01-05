package me.devtools4.cloud.disk.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.devtools4.aops.annotations.TimerMetric.TimerSupplier;
import me.devtools4.aops.aspects.FallbackAspect;
import me.devtools4.aops.aspects.TimerMetricAspect;
import me.devtools4.aops.aspects.TraceAspect;
import org.springframework.context.annotation.Bean;

public class AspectsConfig {

  @Bean
  public TraceAspect traceAspect() {
    return new TraceAspect();
  }

  @Bean
  public TimerMetricAspect timerMetricAspect(TimerSupplier<Timer> timerSupplier) {
    return new TimerMetricAspect(timerSupplier);
  }

  @Bean
  public FallbackAspect fallbackAspect() {
    return new FallbackAspect();
  }

  @Bean
  public TimerSupplier<Timer> simpleTimerSupplier(MetricRegistry metricRegistry) {
    return new SimpleTimerSupplier(metricRegistry, new ConcurrentHashMap<>());
  }

  public static class SimpleTimerSupplier implements TimerSupplier<Timer> {

    private final MetricRegistry registry;
    private final Map<String, Timer> timers;

    public SimpleTimerSupplier(MetricRegistry registry, Map<String, Timer> timers) {
      this.registry = registry;
      this.timers = timers;
    }

    @Override
    public Timer get(Class<?> clazz, String... names) {
      return timers.computeIfAbsent(MetricRegistry.name(clazz, names), registry::timer);
    }
  }
}