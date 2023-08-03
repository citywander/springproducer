package com.sfsf.spring.cdc;

import com.sfsf.spring.cdc.anno.AnnotationParser;
import com.sfsf.spring.cdc.anno.MethodAnnotationInfo;
import com.sfsf.spring.cdc.mapping.UserMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMapping {

	@Test
	public void testAnnotationParser() {
		AnnotationParser mapStructParser = new AnnotationParser();
		mapStructParser.parse(UserMapper.class);
		List<MethodAnnotationInfo> des= mapStructParser.getAnnotationInfos();
		assertThat(mapStructParser.getMethodDescriptions(), hasItem("UserMapper.toDto"));
	}

}
