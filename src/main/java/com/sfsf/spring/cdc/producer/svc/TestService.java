package com.sfsf.spring.cdc.producer.svc;

import org.springframework.stereotype.Service;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;

@Service
public class TestService {
    
    //@SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public void test() {
        System.out.println("Test");
    }

    //@SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) {
        return String.format("Hello at %d", s);
    }
    
    public String helloFallback(long s) {
        return String.format("Halooooo %d", s);
    }

//    public String exceptionHandler(long s, BlockException ex) {
//        // Do some log here.
//        ex.printStackTrace();
//        return "Oops, error occurred at " + s;
//    }

}
