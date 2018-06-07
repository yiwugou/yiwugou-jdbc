package com.yiwugou.jdbc.core.parsing;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDDLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcConstants;
import com.yiwugou.jdbc.core.constant.DbType;
import com.yiwugou.jdbc.core.constant.SqlType;

/**
 * 
 * DruidSqlParser
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:54
 */
public class DruidSqlParser extends AbstractSqlParser {

    @Override
    public SqlType noCache(String sql) {
        List<SQLStatement> stats = new SQLStatementParser(sql).parseStatementList();
        for (SQLStatement stat : stats) {
            if (stat instanceof SQLDDLStatement) {
                return SqlType.DDL;
            }
        }
        for (SQLStatement stat : stats) {
            if (stat instanceof SQLInsertStatement || stat instanceof SQLDeleteStatement
                    || stat instanceof SQLUpdateStatement) {
                return SqlType.DML;
            }
        }
        return SqlType.DQL;
    }

    private static String processDbType(DbType dbType) {
        if (dbType == DbType.MySQL) {
            return JdbcConstants.MYSQL;
        }
        if (dbType == DbType.Oracle) {
            return JdbcConstants.ORACLE;
        }
        throw new UnsupportedOperationException(dbType + " is not supported!");
    }

}
