package com.yiwugou.jdbc.core.invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
public class MethodInvocation {

    @Getter
    private final Method method;

    @Getter
    private final Object[] arguments;

    public void invoke(final Object target) {
        try {
            method.invoke(target, arguments);
        } catch (final IllegalAccessException | InvocationTargetException ex) {
            throw new JdbcException("Invoke jdbc method exception", ex);
        }
    }
}
