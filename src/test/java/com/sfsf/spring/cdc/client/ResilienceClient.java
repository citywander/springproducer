package com.sfsf.spring.cdc.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResilienceClient {
  
  @AliasFor("name")
  String value() default "";

  Class<?> fallback() default void.class;
  
  String path() default "";
  
}
