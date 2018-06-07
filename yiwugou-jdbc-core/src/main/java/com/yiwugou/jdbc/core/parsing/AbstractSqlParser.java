package com.yiwugou.jdbc.core.parsing;

import java.util.HashMap;
import java.util.Map;

import com.yiwugou.jdbc.core.constant.SqlType;

/**
 * 
 * AbstractSqlParser
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月3日 上午10:46:38
 */
public abstract class AbstractSqlParser implements SqlParser {
    private Map<String, SqlType> cache = new HashMap<>();

    @Override
    public SqlType sqlType(String sql) {
        SqlType sqlType = cache.get(sql);
        if (sqlType == null) {
            sqlType = noCache(sql);
            cache.put(sql, sqlType);
        }
        return sqlType;
    }

    protected abstract SqlType noCache(String sql);
}
