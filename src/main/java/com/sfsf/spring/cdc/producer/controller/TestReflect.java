package com.sfsf.spring.cdc.producer.controller;

import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.stereotype.Component;

@Component
public class TestReflect {
  
  @Reflective
  public void testMethod() {
    System.out.println("------111---------");
  }

}
