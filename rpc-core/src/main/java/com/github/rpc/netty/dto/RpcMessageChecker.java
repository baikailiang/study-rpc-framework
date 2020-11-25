package com.github.rpc.netty.dto;


import com.github.rpc.enumeration.RpcResponseCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Verify RpcRequest and RpcRequest
 *
 * @author shuang.kou
 * @createTime 2020年05月26日 18:03:00
 */
@Slf4j
public final class RpcMessageChecker {
    private static final String INTERFACE_NAME = "interfaceName";

    private RpcMessageChecker() {
    }

    public static void check(RpcResponse<Object> rpcResponse, RpcRequest rpcRequest) {
        if (rpcResponse == null) {
        }

        if (!rpcRequest.getRequestId().equals(rpcResponse.getRequestId())) {
        }

        if (rpcResponse.getCode() == null || !rpcResponse.getCode().equals(RpcResponseCode.SUCCESS.getCode())) {
        }
    }
}
