package com.github.rpc.enumeration;

/**
 * @author bkl
 * @date 2020/11/5
 */
public enum RpcConfigProperties {
    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");
    
    private final String propertyValue;
    
    
    RpcConfigProperties(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    
    public String getPropertyValue() {
        return propertyValue;
    }
}
