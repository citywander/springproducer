package com.sfsf.spring.cdc.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfsf.spring.cdc.producer.ErrorMsg;
import com.sfsf.spring.cdc.producer.model.UserDetails;
import com.sfsf.spring.cdc.producer.repo.UserRepository;

@RestController
public class ProducerController {
    
//    @Autowired
//    private TestService testService;
    
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public ErrorMsg getBooks() {
        return new ErrorMsg("123", "Price too large");
        //return new Book(1L, "Alice", 100);
    }
    
    
    //@SentinelResource(value = "ProducerController#getBooksById", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
    public UserDetails getBooksById(@PathVariable Long bookId) {
        Iterable<UserDetails> details = this.userRepository.findAll();
        return userRepository.findById(bookId).get();
    }

}
