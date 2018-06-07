package com.yiwugou.jdbc.core.adapter;

import java.util.ArrayList;
import java.util.Collection;

import com.yiwugou.jdbc.core.exception.JdbcException;
import com.yiwugou.jdbc.core.invocation.ConstructorInvocation;
import com.yiwugou.jdbc.core.invocation.MethodInvocation;

/**
 * 
 * ReplayRecordWrapper
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 下午2:34:39
 */
public class ReplayRecordWrapper extends WrapperAdapter {
    private final Collection<MethodInvocation> methodInvocations = new ArrayList<>();
    private ConstructorInvocation constructorInvocation;

    public final void recordConstructorInvocation(final Class<?> targetClass, final Class<?>[] argumentTypes,
            final Object[] arguments) {
        try {
            constructorInvocation = new ConstructorInvocation(targetClass.getConstructor(argumentTypes), arguments);
        } catch (Exception ex) {
            throw new JdbcException(ex);
        }
    }

    public final Object replayConstructorInvocation() {
        if (this.constructorInvocation != null) {
            return this.constructorInvocation.invoke();
        }
        return null;
    }

    public final void recordMethodInvocation(final Class<?> targetClass, final String methodName,
            final Class<?>[] argumentTypes, final Object[] arguments) {
        try {
            methodInvocations.add(new MethodInvocation(targetClass.getMethod(methodName, argumentTypes), arguments));
        } catch (Exception ex) {
            throw new JdbcException(ex);
        }
    }

    public final void replayMethodsInvocation(final Object target) {
        for (MethodInvocation each : methodInvocations) {
            each.invoke(target);
        }
    }

    public final void clear() {
        this.methodInvocations.clear();
        this.constructorInvocation = null;
    }
}
