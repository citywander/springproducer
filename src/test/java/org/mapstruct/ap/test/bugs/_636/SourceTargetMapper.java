/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._636;


import com.sfsf.platform.common.fastmap.Mapper;
import com.sfsf.platform.common.fastmap.Mappings;
import com.sfsf.platform.common.fastmap.Mapping;
import com.sfsf.platform.common.fastmap.MapperFactory;

@Mapper
public interface SourceTargetMapper extends SourceTargetBaseMapper {
    SourceTargetMapper INSTANCE = MapperFactory.getMapper( SourceTargetMapper.class );

    @Mappings({
        @Mapping(source = "idFoo", target = "foo"),
        @Mapping(source = "idBar", target = "bar")
    })
    Target mapSourceToTarget(Source source);
}
