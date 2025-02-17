package com.wgcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@SpringBootApplication(scanBasePackages = {"com.wgcloud"})
@EnableCaching
@EnableScheduling
public class WgcloudAgentApplication {

  public static void main(String[] args) {
    SpringApplication.run(WgcloudAgentApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
    return restTemplate;
  }

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    return taskScheduler;
  }
}
