package com.yiwugou.jdbc.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.yiwugou.jdbc.core.adapter.AbstractStatementAdapter;
import com.yiwugou.jdbc.core.filter.Filter;
import com.yiwugou.jdbc.core.filter.Invoker;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

import lombok.Getter;

/**
 * 
 * MasterSlaveStatement
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:21:15
 */
@Getter
public final class MasterSlaveStatement extends AbstractStatementAdapter {

    private final MasterSlaveConnection connection;

    private final int resultSetType;

    private final int resultSetConcurrency;

    private final int resultSetHoldability;

    private Statement statement;

    public MasterSlaveStatement(final MasterSlaveConnection connection) {
        this(connection, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    public MasterSlaveStatement(final MasterSlaveConnection connection, final int resultSetType,
            final int resultSetConcurrency) {
        this(connection, resultSetType, resultSetConcurrency, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    public MasterSlaveStatement(final MasterSlaveConnection connection, final int resultSetType,
            final int resultSetConcurrency, final int resultSetHoldability) {
        this.connection = connection;
        this.resultSetType = resultSetType;
        this.resultSetConcurrency = resultSetConcurrency;
        this.resultSetHoldability = resultSetHoldability;
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                return statement.executeQuery(p1);
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate(final String sql) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                int result = statement.executeUpdate(p1);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                int result = statement.executeUpdate(p1, autoGeneratedKeys);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                int result = statement.executeUpdate(p1, columnIndexes);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {

        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                int result = statement.executeUpdate(p1, columnNames);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public boolean execute(final String sql) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                boolean result = statement.execute(p1);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                boolean result = statement.execute(p1, autoGeneratedKeys);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });

    }

    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                boolean result = statement.execute(p1, columnIndexes);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return this.invoker(sql, (p1, p2) -> {
            ProxyDataSource dataSource = proxyDataSource(p1, p2);
            try {
                this.statement = connection.getConnection(dataSource).createStatement(resultSetType,
                        resultSetConcurrency, resultSetHoldability);
                boolean result = statement.execute(p1, columnNames);
                return result;
            } catch (Exception e) {
                dataSource.shutdown();
                throw e;
            }
        });
    }

    private ProxyDataSource proxyDataSource(final String sql, MasterSlaveRule masterSlaveRule) {
        return masterSlaveRule.router(sql);
    }

    @Override
    protected Statement getStatement() {
        return this.statement;
    }

    private <T> T invoker(String sql, Invoker invoker) throws SQLException {
        List<Filter> filters = this.connection.getMasterSlaveDataSource().getMasterSlaveRule().getFilters();
        Invoker<T> chain = Filter.buildFilterChain(filters, invoker);
        try {
            return chain.invoke(sql, connection.getMasterSlaveDataSource().getMasterSlaveRule());
        } catch (Throwable e) {
            throw new SQLException(e);
        }
    }
}