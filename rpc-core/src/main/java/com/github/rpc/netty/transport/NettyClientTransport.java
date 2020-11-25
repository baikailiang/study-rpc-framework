package com.github.rpc.netty.transport;

import com.github.rpc.extension.ExtensionLoader;
import com.github.rpc.factory.SingletonFactory;
import com.github.rpc.netty.dto.RpcRequest;
import com.github.rpc.netty.dto.RpcResponse;
import com.github.rpc.netty.client.provider.ChannelProvider;
import com.github.rpc.netty.client.provider.UnprocessedRequests;
import com.github.rpc.registory.ServiceDiscovery;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @author bkl
 * @date 2020/9/3
 */
public class NettyClientTransport implements ClientTransport{
    
    private final ServiceDiscovery serviceDiscovery;
    private final UnprocessedRequests unprocessedRequests;
    private final ChannelProvider channelProvider;
    
    public NettyClientTransport(){
        this.serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
        this.unprocessedRequests = SingletonFactory.getInstance(UnprocessedRequests.class);
        this.channelProvider = SingletonFactory.getInstance(ChannelProvider.class);
    }
    
    @Override
    public CompletableFuture<RpcResponse<Object>> sendRpcRequest(RpcRequest rpcRequest) {
        CompletableFuture<RpcResponse<Object>> resultFuture = new CompletableFuture<>();
        // 根据请求参数获取rpc服务名称
        String serviceName = rpcRequest.toRpcServiceProperties().toRpcServiceName();
        // 由服务注册发现服务根据rpc服务名称获取rpc服务地址
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(serviceName);
        Channel channel = channelProvider.get(inetSocketAddress);
        if (channel != null && channel.isActive()) {
            // put unprocessed request
            unprocessedRequests.put(rpcRequest.getRequestId(), resultFuture);
            channel.writeAndFlush(rpcRequest).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                
                } else {
                    future.channel().close();
                    resultFuture.completeExceptionally(future.cause());
                }
            });
        } else {
            throw new IllegalStateException();
        }
        return resultFuture;
    }
}
