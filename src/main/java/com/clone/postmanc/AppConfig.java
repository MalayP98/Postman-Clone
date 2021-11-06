package com.clone.postmanc;

import java.util.concurrent.Executor;
import com.clone.postmanc.utils.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfig {

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix(AppConstants.THREAD_NAME);
    executor.initialize();
    return executor;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
