package com.sfsf.spring.cdc.enginex;

import java.util.function.Supplier;

public interface ApiRepository {
    
    public <T> T get(Object key);
    
    public <T> T find(Class<T> type, QueryObject queryObject);
    
    public ApiRepository expand(Supplier<?> supplier);
    

}
