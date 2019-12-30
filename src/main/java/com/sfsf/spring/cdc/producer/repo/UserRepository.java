package com.sfsf.spring.cdc.producer.repo;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import com.sfsf.spring.cdc.producer.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<UserDetails, Long>{
  
//  private EntityManager entityManager;
//
//  @Override
//  public Optional<UserDetails> findById(Long id) {
//    return Optional.of(entityManager.find(UserDetails.class, id));
//  }
  
}