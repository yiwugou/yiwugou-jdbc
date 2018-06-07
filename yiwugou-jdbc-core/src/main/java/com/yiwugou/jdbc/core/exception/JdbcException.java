package com.yiwugou.jdbc.core.exception;

/**
 * 
 * MasterSlaveException
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:41
 */
public class JdbcException extends RuntimeException {

    private static final long serialVersionUID = -1343739516839252250L;

    public JdbcException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public JdbcException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JdbcException(final Throwable cause) {
        super(cause);
    }
}