package com.github.rpc.registory;

import java.util.List;

/**
 * @author bkl
 * @date 2020/11/9
 */
public abstract class AbstractLoadBalance implements LoadBalance{
    @Override
    public String selectServiceAddress(List<String> serviceAddresses) {
        if (serviceAddresses == null || serviceAddresses.size() == 0) {
            return null;
        }
        if (serviceAddresses.size() == 1) {
            return serviceAddresses.get(0);
        }
        return doSelect(serviceAddresses);
    }
    
    protected abstract String doSelect(List<String> serviceAddresses);
}
