package com.sfsf.spring.cdc.anno;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

/**
 * Reads annotation data with <a href="https://asm.ow2.io/">ASM</a> to get annotation with
 * {@link java.lang.annotation.RetentionPolicy#CLASS} visibility at runtime.
 */
public class AnnotationParser {

	private List<String> mapStructAnntotaions = new ArrayList<>();

	private static final Log log = LogFactory.getLog(AnnotationParser.class);
	private List<MethodAnnotationInfo> annotationInfos = new ArrayList<MethodAnnotationInfo>();

	public List<MethodAnnotationInfo> getAnnotationInfos() {
		return annotationInfos;
	}

	public AnnotationParser() {
		mapStructAnntotaions.add(Mapping.class.getSimpleName());
		mapStructAnntotaions.add(InheritInverseConfiguration.class.getSimpleName());
	}

	public Set<String> getMethodDescriptions() {
		return annotationInfos.stream().filter(isMapping()).map(getDescription()).collect(toSet());
	}

	public void parse(Class<?> mapper) {

		ClassInfoCollector classPrinter = new ClassInfoCollector(annotationInfos);
		try {
			ClassReader cr = new ClassReader(mapper.getCanonicalName());
			cr.accept(classPrinter, 0);
		} catch (IOException e) {
			log.error("Failed parsing class info from: " + mapper.getCanonicalName(), e);
		}
	}

	private Function<MethodAnnotationInfo, String> getDescription() {
		return info -> info.getClassName() + "." + info.getMethod();
	}

	private Predicate<MethodAnnotationInfo> isMapping() {
		return info -> mapStructAnntotaions.contains(info.getAnnotationTypeSimple());
	}
}
