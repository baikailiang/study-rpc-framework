package com.github.rpc.registory;

import java.util.List;

/**
 * @author bkl
 * @date 2020/11/9
 */
public interface LoadBalance {
    String selectServiceAddress(List<String> serviceAddresses);
}
