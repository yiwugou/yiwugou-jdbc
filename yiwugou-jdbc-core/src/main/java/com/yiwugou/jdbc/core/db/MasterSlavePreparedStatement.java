
package com.yiwugou.jdbc.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yiwugou.jdbc.core.adapter.AbstractPreparedStatementAdapter;
import com.yiwugou.jdbc.core.filter.Filter;
import com.yiwugou.jdbc.core.filter.Invoker;

import lombok.Getter;

/**
 * 
 * MasterSlavePreparedStatement
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:21:10
 */

public final class MasterSlavePreparedStatement extends AbstractPreparedStatementAdapter {

    private PreparedStatement preparedStatement;

    private ProxyDataSource dataSource;
    @Getter
    private MasterSlaveConnection connection;
    private String sql;
    private Integer resultSetType;
    private Integer resultSetConcurrency;
    private Integer resultSetHoldability;
    private Integer autoGeneratedKeys;
    private int[] columnIndexes;
    private String[] columnNames;

    private int type;

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql) throws SQLException {
        this(connection, sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql,
            final Integer resultSetType, final Integer resultSetConcurrency) throws SQLException {
        this(connection, sql, resultSetType, resultSetConcurrency, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    public MasterSlavePreparedStatement(MasterSlaveConnection masterSlaveConnection, String sql, Integer resultSetType,
            Integer resultSetConcurrency, Integer resultSetHoldability) throws SQLException {
        this(masterSlaveConnection, sql, resultSetType, resultSetConcurrency, resultSetHoldability, null, null, null,
                1);
    }

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql,
            final Integer autoGeneratedKeys) throws SQLException {
        this(connection, sql, null, null, null, autoGeneratedKeys, null, null, 2);
    }

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql,
            final int[] columnIndexes) throws SQLException {
        this(connection, sql, null, null, null, null, columnIndexes, null, 3);
    }

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql,
            final String[] columnNames) throws SQLException {
        this(connection, sql, null, null, null, null, null, columnNames, 4);
    }

    public MasterSlavePreparedStatement(final MasterSlaveConnection connection, final String sql,
            final Integer resultSetType, final Integer resultSetConcurrency, final Integer resultSetHoldability,
            final Integer autoGeneratedKeys, final int[] columnIndexes, final String[] columnNames, int type)
            throws SQLException {
        this.connection = connection;
        this.sql = sql;
        this.resultSetType = resultSetType;
        this.resultSetConcurrency = resultSetConcurrency;
        this.resultSetHoldability = resultSetHoldability;
        this.autoGeneratedKeys = autoGeneratedKeys;
        this.columnIndexes = columnIndexes;
        this.columnNames = columnNames;

        this.type = type;
    }

    private void init() throws SQLException {
        this.dataSource = this.connection.getMasterSlaveDataSource().getMasterSlaveRule().router(sql);
        Connection con = connection.getConnection(dataSource);
        if (type == 1) {
            this.preparedStatement = con.prepareStatement(sql, resultSetType, resultSetConcurrency,
                    resultSetHoldability);
        } else if (type == 2) {
            this.preparedStatement = con.prepareStatement(sql, autoGeneratedKeys);
        } else if (type == 3) {
            this.preparedStatement = con.prepareStatement(sql, columnIndexes);
        } else if (type == 4) {
            this.preparedStatement = con.prepareStatement(sql, columnNames);
        } else {
            throw new UnsupportedOperationException();
        }

    }

    private void shutdown() {
        this.preparedStatement = null;
        this.dataSource.shutdown();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().executeQuery(sql);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().executeUpdate(sql);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().execute(sql);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().executeUpdate(sql, autoGeneratedKeys);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().executeUpdate(sql, columnIndexes);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().executeUpdate(sql, columnNames);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().execute(sql, autoGeneratedKeys);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().execute(sql, columnIndexes);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return getPreparedStatement().execute(sql, columnNames);
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return this.getPreparedStatement().executeBatch();
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public int executeUpdate() throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return this.getPreparedStatement().executeUpdate();
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public boolean execute() throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return this.getPreparedStatement().execute();
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return this.invoker((p1, p2) -> {
            try {
                return this.getPreparedStatement().executeQuery();
            } catch (Exception e) {
                shutdown();
                throw e;
            }
        });
    }

    @Override
    protected PreparedStatement getPreparedStatement() throws SQLException {
        if (this.preparedStatement == null) {
            init();
        }
        return this.preparedStatement;
    }

    private <T> T invoker(Invoker<T> invoker) throws SQLException {
        List<Filter> filters = connection.getMasterSlaveDataSource().getMasterSlaveRule().getFilters();
        Invoker<T> chain = Filter.buildFilterChain(filters, invoker);
        try {
            return chain.invoke(sql, connection.getMasterSlaveDataSource().getMasterSlaveRule());
        } catch (Throwable e) {
            throw new SQLException(e);
        }
    }

}