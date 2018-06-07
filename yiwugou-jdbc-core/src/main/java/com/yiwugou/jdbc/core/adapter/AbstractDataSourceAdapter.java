package com.yiwugou.jdbc.core.adapter;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.yiwugou.jdbc.core.constant.DbType;
import com.yiwugou.jdbc.core.exception.JdbcException;
import com.yiwugou.jdbc.core.util.ThreadUtils;

import lombok.Getter;

public abstract class AbstractDataSourceAdapter extends WrapperAdapter implements DataSource {

    @Getter
    private final DbType dbType;

    private PrintWriter logWriter = new PrintWriter(System.out);

    private DataSource dataSource;

    public AbstractDataSourceAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
        dbType = getDbType(dataSource);
    }

    private DbType getDbType(final DataSource dataSource) {
        if (dataSource instanceof AbstractDataSourceAdapter) {
            return ((AbstractDataSourceAdapter) dataSource).dbType;
        }
        DbType dbType = ThreadUtils.threadCancel(() -> {
            try (Connection connection = dataSource.getConnection()) {
                return DbType.valueFrom(connection.getMetaData().getDatabaseProductName());
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }, 3000L);

        return dbType == null ? DbType.MySQL : dbType;
    }

    @Override
    public final Connection getConnection(final String username, final String password) throws SQLException {
        return getConnection();
    }

    @Override
    public final PrintWriter getLogWriter() {
        return logWriter;
    }

    @Override
    public final void setLogWriter(final PrintWriter out) {
        this.logWriter = out;
    }

    @Override
    public final Logger getParentLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

}