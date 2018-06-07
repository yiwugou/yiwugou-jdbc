package com.yiwugou.jdbc.core.filter;

import com.yiwugou.jdbc.core.db.ProxyDataSource;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

import lombok.Getter;

/**
 * 
 * DefaultInvoker
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 上午11:54:45
 */
public class DefaultInvoker<T> implements Invoker<T> {

    @Getter
    protected ProxyDataSource dataSource;

    @Getter
    protected String sql;

    public DefaultInvoker(String sql) {
        this.sql = sql;
    }

    @Override
    public T invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
