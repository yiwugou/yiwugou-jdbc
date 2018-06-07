package com.yiwugou.jdbc.core.filter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

/**
 * 
 * TimeoutFilter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 上午9:56:47
 */
public class TimeoutFilter extends AbstractFilter {

    private long timeout;

    public TimeoutFilter(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public Object invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
        if (timeout <= 0) {
            return invoker.invoke(sql, masterSlaveRule);
        }
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        Future<Object> future = exec.submit(() -> {
            return this.invoker.invoke(sql, masterSlaveRule);
        });
        try {
            Object obj = future.get(timeout, TimeUnit.MILLISECONDS);
            return obj;
        } catch (Exception e) {
            future.cancel(true);
            throw e;
        }
    }

}
