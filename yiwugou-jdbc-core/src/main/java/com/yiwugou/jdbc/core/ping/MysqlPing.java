package com.yiwugou.jdbc.core.ping;

/**
 * 
 * MysqlPing
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午5:21:15
 */
public class MysqlPing extends AbstractPing {

    @Override
    protected String validationQuery() {
        return "select 1";
    }

}
