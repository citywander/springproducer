package com.sfsf.spring.cdc.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sfsf.spring.cdc.producer.Book;
import com.sfsf.spring.cdc.producer.ErrorMsg;
import com.sfsf.spring.cdc.producer.svc.ExceptionUtil;
import com.sfsf.spring.cdc.producer.svc.TestService;

@RestController
public class ProducerController {
    
    @Autowired
    private TestService testService;
    
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public ErrorMsg getBooks() {
        return new ErrorMsg("123", "Price too large");
        //return new Book(1L, "Alice", 100);
    }
    
    
    @SentinelResource(value = "ProducerController#getBooksById", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
    public Book getBooksById(@PathVariable Integer bookId) {
        return new Book(1L, "Alice", 100);
    }

}
