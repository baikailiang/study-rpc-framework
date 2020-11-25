package com.github.rpc.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.rpc.exception.SerializeException;
import com.github.rpc.netty.dto.RpcRequest;
import com.github.rpc.netty.dto.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author bkl
 * @date 2020/9/2
 */
public class KryoSerializer implements Serializer {
    
    private static final ThreadLocal<Kryo> threadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcRequest.class);
        kryo.register(RpcResponse.class);
        return kryo;
    });
    
    
    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = threadLocal.get();
            kryo.writeObject(output, obj);
            threadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new SerializeException("Serialize fail");
        }
    }
    
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = threadLocal.get();
            Object obj = kryo.readObject(input, clazz);
            threadLocal.remove();
            return clazz.cast(obj);
        } catch (Exception e) {
            throw new SerializeException("deserialize fail");
        }
    }
}
