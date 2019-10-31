package com.sfsf.platform.common.fastmap;

@Mapper
public interface UserMapper {
    
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "org.name", target = "orgName")
    UserDTO mapBOToDTO(UserBO user);
    
    @Mapping(source = "userName", target = "name")
    @Mapping(source = "orgName", target = "org.name")
    UserBO mapDTOToBO(UserDTO user);

}
