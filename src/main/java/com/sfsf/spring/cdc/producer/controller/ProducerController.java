package com.sfsf.spring.cdc.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfsf.spring.cdc.producer.Book;
import com.sfsf.spring.cdc.producer.ErrorMsg;

@RestController
public class ProducerController {
    
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ErrorMsg getBook() {
        return new ErrorMsg("123", "Price too large");
        //return new Book(1L, "Alice", 100);
    }

}
