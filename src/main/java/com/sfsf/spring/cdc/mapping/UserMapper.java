package com.sfsf.spring.cdc.mapping;


import com.sfsf.spring.cdc.models.User;
import com.sfsf.spring.cdc.models.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

	@Mapping(target="userName", source="name")
	@InheritInverseConfiguration
	UserDTO toDto(User User);

	User toUser(UserDTO userDTO);

}
