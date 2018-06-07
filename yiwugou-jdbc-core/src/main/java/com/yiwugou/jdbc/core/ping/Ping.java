package com.yiwugou.jdbc.core.ping;

import com.yiwugou.jdbc.core.db.ProxyDataSource;

/**
 * 
 * Ping
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午5:21:33
 */
public interface Ping {
    boolean isAlive(ProxyDataSource dataSource);
}
