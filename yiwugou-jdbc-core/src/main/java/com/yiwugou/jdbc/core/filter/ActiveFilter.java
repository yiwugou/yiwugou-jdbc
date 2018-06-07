package com.yiwugou.jdbc.core.filter;

import java.util.concurrent.atomic.AtomicInteger;

import com.yiwugou.jdbc.core.exception.JdbcException;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

/**
 * 
 * ActiveFilter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 上午9:56:47
 */
public class ActiveFilter extends AbstractFilter {

    private AtomicInteger data = new AtomicInteger(0);

    private int active;

    public ActiveFilter(int active) {
        this.active = active;
    }

    @Override
    public Object invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
        Object obj = null;
        try {
            if (this.data.incrementAndGet() > active) {
                throw new JdbcException("active is " + active);
            }
            obj = invoker.invoke(sql, masterSlaveRule);
        } finally {
            this.data.decrementAndGet();
        }

        return obj;
    }

}
