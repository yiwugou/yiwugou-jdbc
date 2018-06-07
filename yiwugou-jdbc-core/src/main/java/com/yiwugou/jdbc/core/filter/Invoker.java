package com.yiwugou.jdbc.core.filter;

import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

/**
 * 
 * Invoker
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 上午10:01:10
 */
public interface Invoker<T> {
    public T invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception;
}
