package com.sfsf.spring.cdc.producer.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.sfsf.spring.cdc.producer.model.UserAddress;

public interface UserAddressRepository extends JpaRepositoryImplementation<UserAddress, Long>{

}
