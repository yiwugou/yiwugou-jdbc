package com.yiwugou.jdbc.core.adapter;

import java.sql.Wrapper;

/**
 * 
 * WrapperAdapter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午2:27:14
 */
public class WrapperAdapter implements Wrapper {

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return iface != null && iface.isInstance(this);

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap(Class<T> iface) {
        if (iface == null) {
            return null;
        }

        if (iface.isInstance(this)) {
            return (T) this;
        }

        return null;
    }

}
