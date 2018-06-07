
package com.yiwugou.jdbc.core.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * 
 * AbstractStatementAdapter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:19:50
 */
public abstract class AbstractStatementAdapter extends WrapperAdapter implements Statement {

    @Override
    public final void close() throws SQLException {
        getStatement().close();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return getStatement().isClosed();
    }

    @Override
    public final boolean isPoolable() throws SQLException {
        return getStatement().isPoolable();
    }

    @Override
    public final void setPoolable(final boolean poolable) throws SQLException {
        getStatement().setPoolable(poolable);
    }

    @Override
    public final int getFetchSize() throws SQLException {
        return getStatement().getFetchSize();
    }

    @Override
    public final void setFetchSize(final int rows) throws SQLException {
        getStatement().setFetchSize(rows);
    }

    @Override
    public final void setEscapeProcessing(final boolean enable) throws SQLException {
        getStatement().setEscapeProcessing(enable);
    }

    @Override
    public final void cancel() throws SQLException {
        getStatement().cancel();
    }

    @Override
    public final int getUpdateCount() throws SQLException {
        long result = 0;
        boolean hasResult = false;
        if (getStatement().getUpdateCount() > -1) {
            hasResult = true;
        }
        result += getStatement().getUpdateCount();
        if (result > Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        }
        return hasResult ? Long.valueOf(result).intValue() : -1;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.getStatement().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        getStatement().clearWarnings();
    }

    @Override
    public final boolean getMoreResults() throws SQLException {
        return getStatement().getMoreResults();
    }

    @Override
    public final boolean getMoreResults(final int current) throws SQLException {
        return getStatement().getMoreResults(current);
    }

    @Override
    public final int getMaxFieldSize() throws SQLException {
        return getStatement().getMaxFieldSize();
    }

    @Override
    public final void setMaxFieldSize(final int max) throws SQLException {
        getStatement().setMaxFieldSize(max);
    }

    @Override
    public final int getMaxRows() throws SQLException {
        return getStatement().getMaxRows();
    }

    @Override
    public final void setMaxRows(final int max) throws SQLException {
        getStatement().setMaxRows(max);
    }

    @Override
    public final int getQueryTimeout() throws SQLException {
        return getStatement().getQueryTimeout();
    }

    @Override
    public final void setQueryTimeout(final int seconds) throws SQLException {
        getStatement().setQueryTimeout(seconds);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return this.getStatement().getGeneratedKeys();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return this.getStatement().getResultSet();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        getStatement().setCursorName(name);
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        getStatement().setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return getStatement().getFetchDirection();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        getStatement().addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        getStatement().clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return getStatement().executeBatch();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        getStatement().closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return getStatement().isCloseOnCompletion();
    }

    protected abstract Statement getStatement() throws SQLException;
}
