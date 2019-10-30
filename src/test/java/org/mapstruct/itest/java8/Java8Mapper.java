/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.itest.java8;

import com.sfsf.platform.common.fastmap.Mapper;
import com.sfsf.platform.common.fastmap.MapperFactory;
import com.sfsf.platform.common.fastmap.Mapping;

@Mapper
public interface Java8Mapper {

    Java8Mapper INSTANCE = MapperFactory.getMapper( Java8Mapper.class );

    @Mapping(source = "firstName", target = "givenName")
    @Mapping(source = "lastName", target = "surname")
    Target sourceToTarget(Source source);
}
