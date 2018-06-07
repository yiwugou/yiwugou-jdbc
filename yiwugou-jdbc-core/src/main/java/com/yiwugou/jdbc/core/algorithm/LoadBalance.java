
package com.yiwugou.jdbc.core.algorithm;

import java.util.List;

import com.yiwugou.jdbc.core.constant.SqlType;
import com.yiwugou.jdbc.core.db.ProxyDataSource;

/**
 * 
 * LoadBalanceAlgorithm
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:19:59
 */
public interface LoadBalance {
    ProxyDataSource select(SqlType sqlType, ProxyDataSource masterDataSource, List<ProxyDataSource> slaveDataSources);
}
