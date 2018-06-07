
package com.yiwugou.jdbc.core.algorithm;

import java.util.List;
import java.util.Random;

import com.yiwugou.jdbc.core.db.ProxyDataSource;

/**
 * 
 * RandomLoadBalanceAlgorithm
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:03
 */
public final class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected ProxyDataSource loadbalance(List<ProxyDataSource> slaveDataSources) {
        return slaveDataSources.get(new Random().nextInt(slaveDataSources.size()));
    }
}
