package com.yiwugou.jdbc.core.algorithm;

import java.util.List;
import java.util.stream.Collectors;

import com.yiwugou.jdbc.core.constant.SqlType;
import com.yiwugou.jdbc.core.db.ProxyDataSource;
import com.yiwugou.jdbc.core.exception.JdbcException;

/**
 * 
 * AbstractLoadBalanceAlgorithm
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:37:58
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public ProxyDataSource select(SqlType sqlType, ProxyDataSource masterDataSource,
            List<ProxyDataSource> slaveDataSources) {
        boolean isDql = sqlType == SqlType.DQL;
        if (masterDataSource.isAlived()) {
            return masterDataSource;
        }

        if (!isDql) {// such as DML
            throw new JdbcException("Master is not alived,can not support DML!");
        }

        List<ProxyDataSource> slaveds = slaveDataSources.parallelStream().filter(e -> e.isAlived())
                .collect(Collectors.toList());
        if (slaveds == null || slaveds.size() == 0) {
            throw new JdbcException("No DB is avialble!");
        }
        if (slaveds.size() == 1) {
            return slaveDataSources.get(0);
        }
        return this.loadbalance(slaveds);
    }

    protected abstract ProxyDataSource loadbalance(List<ProxyDataSource> slaveDataSources);

}
