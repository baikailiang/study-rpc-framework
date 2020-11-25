package com.github.rpc.registory;

import java.util.List;
import java.util.Random;

/**
 * @author bkl
 * @date 2020/11/9
 */
public class RandomLoadBalance extends AbstractLoadBalance{
    @Override
    protected String doSelect(List<String> serviceAddresses) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
