
package com.yiwugou.jdbc.core.adapter;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 
 * AbstractConnectionAdapter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:19:40
 */
public abstract class AbstractConnectionAdapter extends ReplayRecordWrapper implements Connection {

    protected abstract Connection getConnection();

    @Override
    public final boolean getAutoCommit() throws SQLException {
        return getConnection().getAutoCommit();
    }

    @Override
    public final void setAutoCommit(final boolean autoCommit) throws SQLException {
        recordMethodInvocation(Connection.class, "setAutoCommit", new Class[] { boolean.class },
                new Object[] { autoCommit });
        getConnection().setAutoCommit(autoCommit);
    }

    @Override
    public final void commit() throws SQLException {
        this.getConnection().commit();
    }

    @Override
    public final void rollback() throws SQLException {
        this.getConnection().rollback();
    }

    @Override
    public void close() throws SQLException {
        this.getConnection().close();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.getConnection().isClosed();
    }

    @Override
    public final boolean isReadOnly() throws SQLException {
        return this.getConnection().isReadOnly();
    }

    @Override
    public final void setReadOnly(final boolean readOnly) throws SQLException {
        recordMethodInvocation(Connection.class, "setReadOnly", new Class[] { boolean.class },
                new Object[] { readOnly });
        this.getConnection().setReadOnly(readOnly);
    }

    @Override
    public final int getTransactionIsolation() throws SQLException {
        return this.getConnection().getTransactionIsolation();
    }

    @Override
    public final void setTransactionIsolation(final int level) throws SQLException {
        recordMethodInvocation(Connection.class, "setTransactionIsolation", new Class[] { int.class },
                new Object[] { level });
        this.getConnection().setTransactionIsolation(level);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return this.getConnection().prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return this.getConnection().nativeSQL(sql);
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {

        this.getConnection().setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return this.getConnection().getCatalog();
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.getConnection().getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        this.getConnection().setTypeMap(map);
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return this.getConnection().setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return this.getConnection().setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        this.getConnection().rollback(savepoint);

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.getConnection().releaseSavepoint(savepoint);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        return this.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public Clob createClob() throws SQLException {
        return this.getConnection().createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return this.getConnection().createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return this.getConnection().createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return this.getConnection().createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return this.getConnection().isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.getConnection().setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.getConnection().setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return this.getConnection().getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return this.getConnection().getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return this.getConnection().createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return this.getConnection().createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        this.getConnection().setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return this.getConnection().getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        this.getConnection().abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        this.getConnection().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return this.getConnection().getNetworkTimeout();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.getConnection().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        this.getConnection().clearWarnings();
    }

    @Override
    public final int getHoldability() throws SQLException {
        return this.getConnection().getHoldability();
    }

    @Override
    public final void setHoldability(final int holdability) throws SQLException {
        this.getConnection().setHoldability(holdability);
    }
}
