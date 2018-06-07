package com.yiwugou.jdbc.core.invocation;

import java.lang.reflect.Constructor;

import com.yiwugou.jdbc.core.exception.JdbcException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 
 * JdbcMethodInvocation
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 下午2:44:30
 */
@RequiredArgsConstructor
public class ConstructorInvocation {

    @Getter
    private final Constructor constructor;

    @Getter
    private final Object[] arguments;

    public Object invoke() {
        try {
            return constructor.newInstance(arguments);
        } catch (Exception e) {
            throw new JdbcException(e);
        }
    }
}
