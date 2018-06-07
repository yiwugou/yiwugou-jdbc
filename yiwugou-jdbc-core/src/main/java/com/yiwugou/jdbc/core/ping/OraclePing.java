package com.yiwugou.jdbc.core.ping;

/**
 * 
 * OraclePing
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午5:21:20
 */
public class OraclePing extends AbstractPing {

    @Override
    protected String validationQuery() {
        return "select 1 from dual ";
    }

}
