package com.github.rpc.netty.transport;

import com.github.rpc.netty.dto.RpcRequest;

/**
 * 发送RpcRequest
 * @author bkl
 * @date 2020/9/3
 */
public interface ClientTransport {
    Object sendRpcRequest(RpcRequest rpcRequest);
}
