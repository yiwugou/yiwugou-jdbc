package com.yiwugou.jdbc.core.adapter;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 
 * AbstractMasterSlavePreparedStatementAdapter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:19:45
 */
public abstract class AbstractPreparedStatementAdapter extends AbstractStatementAdapter implements PreparedStatement {
    @Override
    public final void setNull(final int parameterIndex, final int sqlType) throws SQLException {
        getPreparedStatement().setNull(parameterIndex, sqlType);
    }

    @Override
    public final void setNull(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
        getPreparedStatement().setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public final void setBoolean(final int parameterIndex, final boolean x) throws SQLException {
        getPreparedStatement().setBoolean(parameterIndex, x);
    }

    @Override
    public final void setByte(final int parameterIndex, final byte x) throws SQLException {
        getPreparedStatement().setByte(parameterIndex, x);
    }

    @Override
    public final void setShort(final int parameterIndex, final short x) throws SQLException {
        getPreparedStatement().setShort(parameterIndex, x);
    }

    @Override
    public final void setInt(final int parameterIndex, final int x) throws SQLException {
        getPreparedStatement().setInt(parameterIndex, x);
    }

    @Override
    public final void setLong(final int parameterIndex, final long x) throws SQLException {
        getPreparedStatement().setLong(parameterIndex, x);
    }

    @Override
    public final void setFloat(final int parameterIndex, final float x) throws SQLException {
        getPreparedStatement().setFloat(parameterIndex, x);
    }

    @Override
    public final void setDouble(final int parameterIndex, final double x) throws SQLException {
        getPreparedStatement().setDouble(parameterIndex, x);
    }

    @Override
    public final void setString(final int parameterIndex, final String x) throws SQLException {
        getPreparedStatement().setString(parameterIndex, x);
    }

    @Override
    public final void setBigDecimal(final int parameterIndex, final BigDecimal x) throws SQLException {
        getPreparedStatement().setBigDecimal(parameterIndex, x);
    }

    @Override
    public final void setDate(final int parameterIndex, final Date x) throws SQLException {
        getPreparedStatement().setDate(parameterIndex, x);
    }

    @Override
    public final void setDate(final int parameterIndex, final Date x, final Calendar cal) throws SQLException {
        getPreparedStatement().setDate(parameterIndex, x, cal);
    }

    @Override
    public final void setTime(final int parameterIndex, final Time x) throws SQLException {
        getPreparedStatement().setTime(parameterIndex, x);
    }

    @Override
    public final void setTime(final int parameterIndex, final Time x, final Calendar cal) throws SQLException {
        getPreparedStatement().setTime(parameterIndex, x, cal);
    }

    @Override
    public final void setTimestamp(final int parameterIndex, final Timestamp x) throws SQLException {
        getPreparedStatement().setTimestamp(parameterIndex, x);
    }

    @Override
    public final void setTimestamp(final int parameterIndex, final Timestamp x, final Calendar cal)
            throws SQLException {
        getPreparedStatement().setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public final void setBytes(final int parameterIndex, final byte[] x) throws SQLException {
        getPreparedStatement().setBytes(parameterIndex, x);
    }

    @Override
    public final void setBlob(final int parameterIndex, final Blob x) throws SQLException {
        getPreparedStatement().setBlob(parameterIndex, x);
    }

    @Override
    public final void setBlob(final int parameterIndex, final InputStream x) throws SQLException {
        getPreparedStatement().setBlob(parameterIndex, x);
    }

    @Override
    public final void setBlob(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        getPreparedStatement().setBlob(parameterIndex, x, length);
    }

    @Override
    public final void setClob(final int parameterIndex, final Clob x) throws SQLException {
        getPreparedStatement().setClob(parameterIndex, x);
    }

    @Override
    public final void setClob(final int parameterIndex, final Reader x) throws SQLException {
        getPreparedStatement().setClob(parameterIndex, x);
    }

    @Override
    public final void setClob(final int parameterIndex, final Reader x, final long length) throws SQLException {
        getPreparedStatement().setClob(parameterIndex, x, length);
    }

    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x) throws SQLException {
        getPreparedStatement().setAsciiStream(parameterIndex, x);
    }

    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x, final int length)
            throws SQLException {
        getPreparedStatement().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x, final long length)
            throws SQLException {
        getPreparedStatement().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public final void setUnicodeStream(final int parameterIndex, final InputStream x, final int length)
            throws SQLException {
        getPreparedStatement().setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x) throws SQLException {
        getPreparedStatement().setBinaryStream(parameterIndex, x);
    }

    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x, final int length)
            throws SQLException {
        getPreparedStatement().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x, final long length)
            throws SQLException {
        getPreparedStatement().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x) throws SQLException {
        getPreparedStatement().setCharacterStream(parameterIndex, x);
    }

    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x, final int length)
            throws SQLException {
        getPreparedStatement().setCharacterStream(parameterIndex, x, length);
    }

    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x, final long length)
            throws SQLException {
        getPreparedStatement().setCharacterStream(parameterIndex, x, length);
    }

    @Override
    public void setSQLXML(final int parameterIndex, final SQLXML x) throws SQLException {
        getPreparedStatement().setSQLXML(parameterIndex, x);
    }

    @Override
    public void setURL(final int parameterIndex, final URL x) throws SQLException {
        getPreparedStatement().setURL(parameterIndex, x);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x) throws SQLException {
        getPreparedStatement().setObject(parameterIndex, x);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType) throws SQLException {
        getPreparedStatement().setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType, final int scaleOrLength)
            throws SQLException {
        getPreparedStatement().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public final void clearParameters() throws SQLException {
        getPreparedStatement().clearParameters();
    }

    @Override
    public void clearBatch() throws SQLException {
        this.getPreparedStatement().clearBatch();
    }

    @Override
    public void addBatch() throws SQLException {
        this.getPreparedStatement().addBatch();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return this.getPreparedStatement().getGeneratedKeys();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return this.getPreparedStatement().getResultSetHoldability();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return this.getPreparedStatement().getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return this.getPreparedStatement().getResultSetType();
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        this.getPreparedStatement().setRef(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        this.getPreparedStatement().setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return this.getPreparedStatement().getMetaData();
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return this.getPreparedStatement().getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        this.getPreparedStatement().setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        this.getPreparedStatement().setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        this.getPreparedStatement().setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        this.getPreparedStatement().setNClob(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        this.getPreparedStatement().setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        this.getPreparedStatement().setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        this.getPreparedStatement().setNClob(parameterIndex, reader);
    }

    @Override
    protected Statement getStatement() throws SQLException {
        return getPreparedStatement();
    }

    protected abstract PreparedStatement getPreparedStatement() throws SQLException;
}
