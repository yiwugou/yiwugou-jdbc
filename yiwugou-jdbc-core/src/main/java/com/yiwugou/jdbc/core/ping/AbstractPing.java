package com.yiwugou.jdbc.core.ping;

import java.sql.Connection;
import java.sql.SQLException;

import com.yiwugou.jdbc.core.db.ProxyDataSource;

/**
 * 
 * AbstractPing
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午5:21:09
 */
public abstract class AbstractPing implements Ping {

    @Override
    public boolean isAlive(ProxyDataSource dataSource) {
        String sql = validationQuery();
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement(sql).executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract String validationQuery();

}
