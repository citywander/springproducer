package com.sfsf.spring.cdc.producer.obs;

import org.springframework.stereotype.Service;
import io.micrometer.observation.annotation.Observed;

@Observed(name = "greetingService")
@Service
public class GreetingService {

  public String sayHello() {
    return "Hello World!";
  }
}
