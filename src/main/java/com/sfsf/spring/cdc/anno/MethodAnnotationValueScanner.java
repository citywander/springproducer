package com.sfsf.spring.cdc.anno;

import org.objectweb.asm.AnnotationVisitor;

import static com.sfsf.spring.cdc.anno.ASMVersion.ASMversion;

public class MethodAnnotationValueScanner extends AnnotationVisitor {
	MethodAnnotationValueScanner() { super(ASMversion); }

	@Override
	public void visit(String name, Object value) {
		System.out.println("visitMethodAnnotationValue: " + name + " = " + value);
		super.visit(name, value);
	}
}