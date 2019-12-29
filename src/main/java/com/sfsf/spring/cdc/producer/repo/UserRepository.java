package com.sfsf.spring.cdc.producer.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfsf.spring.cdc.producer.model.UserDetails;

@Repository
public interface UserRepository extends CrudRepository<UserDetails, Long> {

}
