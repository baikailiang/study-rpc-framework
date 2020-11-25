package com.github.rpc.kryo;

/**
 * @author bkl
 * @date 2020/9/2
 */
public interface Serializer {
    
    byte[] serialize(Object obj);
    
    <T> T deserialize(byte[] bytes, Class<T> clazz);
    
}
