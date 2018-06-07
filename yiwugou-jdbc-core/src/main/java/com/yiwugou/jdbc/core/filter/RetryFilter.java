package com.yiwugou.jdbc.core.filter;

import com.yiwugou.jdbc.core.db.ProxyDataSource;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

/**
 * 
 * RetryFilter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 上午10:01:17
 */
public class RetryFilter extends AbstractFilter {

    private int retries;

    public RetryFilter(int retries) {
        this.retries = retries;
    }

    @Override
    public Object invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
        int retry = retries;
        while (true) {
            try {
                Object obj = invoker.invoke(sql, masterSlaveRule);
                return obj;
            } catch (Exception e) {
                if (--retry < 0) {
                    throw e;
                }
            }
        }
    }

}
