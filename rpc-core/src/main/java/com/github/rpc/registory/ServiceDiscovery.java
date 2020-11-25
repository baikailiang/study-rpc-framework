package com.github.rpc.registory;

import com.github.rpc.extension.SPI;

import java.net.InetSocketAddress;

/**
 * @author bkl
 * @date 2020/10/19
 */
@SPI
public interface ServiceDiscovery {

    InetSocketAddress lookupService(String rpcServiceName);
}
