package com.sfsf.spring.cdc.consumer;

import java.lang.annotation.Annotation;

import org.springframework.core.annotation.AnnotationUtils;

public class Test {

    @org.junit.Test
    public void test() {
        Annotation annotations  = AnnotationUtils.findAnnotation(TestBean.class, TestAnno.class);

    }

}
