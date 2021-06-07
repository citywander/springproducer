package com.sfsf.spring.cdc.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringproducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringproducerApplication.class, args);
  }

}
