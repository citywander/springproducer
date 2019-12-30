package com.sfsf.spring.cdc.producer.controller;

import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
//    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
//    public UserDetails getBooksById(@PathVariable Long bookId) {
//        return userRepository.findById(bookId).get();
//    }
    
    @RequestMapping(value = "/books/{email}", method = RequestMethod.GET)
    public List<UserDetails> getBooksByName(@PathVariable String email) {
        return userRepository.findAll(getUserDetailsByName(email));
    }
    
    public static Specification<UserDetails> getUserDetailsByName(String email) {
      return (root, query, criteriaBuilder) -> {
        Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
        return predicate;
      };
  }

}
