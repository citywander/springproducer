package com.sfsf.spring.cdc.anno;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

import static com.sfsf.spring.cdc.anno.ASMVersion.ASMversion;

class MethodInfoCollector extends MethodVisitor {

	private final String methodName;
	private final String className;
	private final List<MethodAnnotationInfo> mapStructParser;

	public MethodInfoCollector(String method, String className, List<MethodAnnotationInfo> mapStructParser) {
		super(ASMversion);
		this.methodName = method;
		this.className = className;
		this.mapStructParser = mapStructParser;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
		return new MethodAnnotationInfoCollector(methodName, descriptor, className, mapStructParser);
	}
}
