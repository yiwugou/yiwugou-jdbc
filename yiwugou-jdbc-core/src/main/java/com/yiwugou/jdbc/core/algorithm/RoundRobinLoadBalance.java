package com.yiwugou.jdbc.core.algorithm;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yiwugou.jdbc.core.db.ProxyDataSource;

/**
 * 
 * RoundRobinLoadBalanceAlgorithm
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:14
 */
public final class RoundRobinLoadBalance extends AbstractLoadBalance {

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    protected ProxyDataSource loadbalance(List<ProxyDataSource> slaveDataSources) {
        return slaveDataSources.get(COUNT.getAndIncrement() % slaveDataSources.size());
    }
}
