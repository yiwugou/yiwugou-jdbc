package com.yiwugou.jdbc.core.filter;

import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

public class MockFilter extends AbstractFilter {

    @Override
    public Object invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
        try {
            Object obj = invoker.invoke(sql, masterSlaveRule);
            return obj;
        } catch (Exception e) {
            // ignore
            //             e.printStackTrace();
            //            return null;
            throw e;
        }
    }

}
