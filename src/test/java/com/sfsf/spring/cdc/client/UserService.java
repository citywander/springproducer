package com.sfsf.spring.cdc.client;

import org.springframework.web.bind.annotation.RequestMapping;

@ResilienceClient(fallback=UserServiceCallback.class)
public abstract class UserService {
  
  private ApiClient client;

  /**
   * Skip fallback in UserServiceCallback
   * @return
   */
  @RequestMapping("GET /greeting")
  abstract String getGreeting();
  
  @RequestMapping("POST /greeting")            
  abstract String createGreeting();
  
  @RequestMapping("POST /hello")
  public String hello() {
    return (String)client.invoke("aa");
  }

}
