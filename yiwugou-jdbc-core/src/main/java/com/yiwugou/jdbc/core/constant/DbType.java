package com.yiwugou.jdbc.core.constant;

/**
 * 
 * DbType
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:25
 */
public enum DbType {

    H2("H2"), MySQL("MySQL"), Oracle("Oracle"), SQLServer("Microsoft SQL Server"), PostgreSQL("PostgreSQL");

    private final String productName;

    DbType(final String productName) {
        this.productName = productName;
    }

    public static DbType valueFrom(final String databaseName) {
        for (DbType dbType : DbType.values()) {
            if (dbType.productName.equalsIgnoreCase(databaseName)) {
                return dbType;
            }
        }

        throw new UnsupportedOperationException(String.format("Can not support database type [%s].", databaseName));
    }
}