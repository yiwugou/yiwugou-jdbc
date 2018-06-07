package com.yiwugou.jdbc.core.ping;

import com.yiwugou.jdbc.core.constant.DbType;

/**
 * 
 * PingFactory
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午5:21:37
 */
public class PingFactory {
    public static Ping create(DbType dbType) {
        if (dbType == DbType.Oracle) {
            return new OraclePing();
        }
        return new MysqlPing();
    }
}
