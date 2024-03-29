package com.sfsf.spring.cdc.producer.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfsf.spring.cdc.producer.ErrorMsg;
import com.sfsf.spring.cdc.producer.model.UserAddress;
import com.sfsf.spring.cdc.producer.model.UserDetails;
import com.sfsf.spring.cdc.producer.obs.GreetingService;
import com.sfsf.spring.cdc.producer.repo.UserAddressRepository;
import com.sfsf.spring.cdc.producer.repo.UserRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;

@RestController
public class ProducerController {

  // @Autowired
  // private TestService testService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserAddressRepository addressRepository;

  @Autowired
  private TestReflect testReflect;

  @Autowired
  private GreetingService gs;

  @GetMapping(value = "/errors")
  public ErrorMsg getBooks() {
    try {
      Method m = testReflect.getClass().getMethod("testMethod");
      m.invoke(testReflect);
    } catch (NoSuchMethodException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new ErrorMsg("123", "Price too large");
    // return new Book(1L, "Alice", 100);
  }


  // @SentinelResource(value = "ProducerController#getBooksById", blockHandler = "handleException",
  // blockHandlerClass = {ExceptionUtil.class})
  // @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
  // public UserDetails getBooksById(@PathVariable Long bookId) {
  // return userRepository.findById(bookId).get();
  // }

  @RequestMapping(value = "/books/{email}", method = RequestMethod.GET)
  public List<UserDetails> getBooksByName(@PathVariable String email) {
    return userRepository.findAll(getUserDetailsByName(email));
  }

  @RequestMapping(value = "/UserAddress/{userId}", method = RequestMethod.GET)
  public List<UserAddress> getUserAddress(@PathVariable Long userId) {
    gs.sayHello();
    return addressRepository.findAll(getUserAddressById(userId));
  }

  @RequestMapping(value = "/UserAddress", method = RequestMethod.POST)
  public UserAddress addUserAddress(@Valid @RequestBody UserAddress userAddress) {
    // Optional<UserDetails> user = userRepository.findById(1L);
    // userAddress.setUser(user.get());
    return addressRepository.save(userAddress);
  }

  public static Specification<UserAddress> getUserAddressById(Long userId) {
    return (root, query, criteriaBuilder) -> {
      // Root parentRoot = criteriaBuilder.
      Predicate predicate = criteriaBuilder.equal(root.get("id"), userId);
      return predicate;
    };
  }

  public static Specification<UserDetails> getUserDetailsByName(String email) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
      return predicate;
    };
  }

}
