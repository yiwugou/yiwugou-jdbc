
package com.yiwugou.jdbc.core.constant;

/**
 * 
 * SqlType
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:28
 */
public enum SqlType {

    /**
     * Data Query Language.
     * 
     * <p>
     * Such as {@code SELECT}.
     * </p>
     */
    DQL,

    /**
     * Data Manipulation Language.
     *
     * <p>
     * Such as {@code INSERT}, {@code UPDATE}, {@code DELETE}.
     * </p>
     */
    DML,

    /**
     * Data Definition Language.
     *
     * <p>
     * Such as {@code CREATE}, {@code ALTER}, {@code DROP}, {@code TRUNCATE}.
     * </p>
     */
    DDL,

    /**
     * Transaction Control Language.
     *
     * <p>
     * Such as {@code SET}, {@code COMMIT}, {@code ROLLBACK},
     * {@code SAVEPOIINT}, {@code BEGIN}.
     * </p>
     */
    TCL,

    /**
     * Database administrator Language.
     */
    DAL;

}
