package com.sfsf.spring.cdc.producer;

import java.lang.reflect.Method;


import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;
import jakarta.annotation.Priority;
import jakarta.servlet.http.HttpServletRequest;

@Service
@Priority(-1)
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    
    
    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) throws Exception {
            String lookupPath = getUrlPathHelper().getLookupPathForRequest(request);
            if (lookupPath.contains("UserAddress")) {
                lookupPath = lookupPath.replaceAll("UserAddress1", "UserAddress");
            }
            request.setAttribute(UrlPathHelper.PATH_ATTRIBUTE, lookupPath);
            HandlerMethod handlerMethod = lookupHandlerMethod(lookupPath, request);
            return (handlerMethod != null ? handlerMethod.createWithResolvedBean() : null);
    }
    
    @Override
    public int getOrder() {
            return -1;
    }
    
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return null;
    }

}
