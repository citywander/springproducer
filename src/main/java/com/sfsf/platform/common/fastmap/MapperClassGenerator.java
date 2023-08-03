package com.sfsf.platform.common.fastmap;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

public class MapperClassGenerator {
    
    public MapperClassGenerator() {
    }
    
    public <T> Class<T> generate(Class<T> type) throws CannotCompileException, IntrospectionException {
        Mapper mapper = type.getAnnotation(Mapper.class);
        ClassPool pool = new ClassPool(true);
        ClassLoader cl = type.getClassLoader();
        pool.insertClassPath(new LoaderClassPath(cl));

        String pkgName = type.getPackage().getName();
        String className = pkgName + "." + type.getSimpleName() + mapper.implSuffixName();
        CtClass ct = pool.makeClass(className);
        ct.setInterfaces(new CtClass[]{pool.makeInterface(type.getName())});
        renderMapperMethod(type, ct);
        Class<T> to = (Class<T>)ct.toClass();

        return to;
    }
    
    private <T> void renderMapperMethod(Class<T> type, CtClass ct) throws CannotCompileException, IntrospectionException {
        Method[] methods = type.getDeclaredMethods();
        
        for (Method method: methods) {
            StringBuilder sb = new StringBuilder();
            if (method.isDefault()) {
                continue;
            }
            sb.append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append(" (") ;
            Class<?>[] parameterTypes = method.getParameterTypes();
            boolean appendComma = parameterTypes.length > 1? true: false;
            
            for (int i = 0; i < parameterTypes.length; i++){
                if (appendComma && i < parameterTypes.length) {
                    sb.append(parameterTypes[i].getName()+" p" + i + ",");
                } else {
                    sb.append(parameterTypes[i].getName()+" p" + i);
                }
            }
            sb.append(") {").append("\n  ");
            sb.append(method.getReturnType().getName()).append(" r = new ").append(method.getReturnType().getName()).append("();");
            sb.append("\n");
            Mapping[] mappings = method.getDeclaredAnnotationsByType(Mapping.class);
            addAllFields(sb, mappings, parameterTypes[0], method.getReturnType());
            sb.append("return r;");
            sb.append("\n").append("}");
            System.out.println(sb.toString());
            ct.addMethod(CtMethod.make(sb.toString(), ct));
        }
    }
    
    private void addAllFields(StringBuilder sb, Mapping[] mappings, Class<?> srcType, Class<?> targetType) throws IntrospectionException {
        Map<String, String> mappingDict = new HashMap<>();
        for (Mapping mapping: mappings) {
            mappingDict.put(mapping.target(), mapping.source());
        }
        for (PropertyDescriptor pd : Introspector.getBeanInfo(targetType).getPropertyDescriptors()) {
            String readPd = pd.getName();
            if (mappingDict.containsKey(pd.getName())) {
                readPd = mappingDict.get(pd.getName());
            }
            if (pd.getWriteMethod() != null && !"class".equals(pd.getName())) {
                Method srcReadMethod = null;
                try {
                    srcReadMethod = new PropertyDescriptor(readPd, srcType).getReadMethod();
                } catch (IntrospectionException e) {
                    continue;
                }
                mappingDict.remove(pd.getName());
                sb.append("r.").append(pd.getWriteMethod().getName()).append("(").append("p0.").append(srcReadMethod.getName()).append("());");
                sb.append("\n");
            }
        }
        for(Entry<String, String> stEntry: mappingDict.entrySet()) {
            String targetFn = stEntry.getKey();
            String[] targetFns = targetFn.split("[.]");
            for (int i = 0; i < targetFns.length; i++) {
                try {
                    PropertyDescriptor targetWriteMethod = new PropertyDescriptor(targetFns[i], targetType);
                    Class<?> propType = targetWriteMethod.getPropertyType();
                    if (!propType.getName().startsWith("java")) {
                        String param = propType.getSimpleName().toLowerCase() + i;
                        sb.append(propType.getName()).append(" ");
                        sb.append(param).append(" = new " + propType.getName() + "();\n");
                    }
                } catch (IntrospectionException e) {
                    continue;
                }
            }
        }
        
    }

}
