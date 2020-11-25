package com.github.rpc.netty.dto;

import com.github.rpc.enumeration.RpcMsgType;
import lombok.*;

/**
 * @author bkl
 * @date 2020/9/3
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest {
    
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private RpcMsgType rpcMessageType;
    private String version;
    private String group;
    
    public RpcServiceProperties toRpcServiceProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
    
}
