package com.yiwugou.jdbc.core.parsing;

import com.yiwugou.jdbc.core.constant.SqlType;

import io.shardingjdbc.core.constant.SQLType;
import io.shardingjdbc.core.parsing.SQLJudgeEngine;
import io.shardingjdbc.core.parsing.parser.sql.SQLStatement;

/**
 * 
 * ShardingJdbcSqlParser
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:57
 */
public class ShardingJdbcSqlParser extends AbstractSqlParser {

    @Override
    public SqlType noCache(String sql) {
        SQLStatement stat = new SQLJudgeEngine(sql).judge();
        SQLType sqlType = stat.getType();
        return SqlType.valueOf(sqlType.toString());
    }

}
