package com.yiwugou.jdbc.core.parsing;

import com.yiwugou.jdbc.core.constant.SqlType;

/**
 * 
 * SqlParser
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月3日 上午10:46:46
 */
public interface SqlParser {
    SqlType sqlType(String sql);
}
