package com.github.rpc.netty.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bkl
 * @date 2020/9/3
 */
@Builder
@Getter
@Setter
public class RpcServiceProperties {
    private String version;
    private String group;
    private String serviceName;
    
    public String toRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }
    
}
