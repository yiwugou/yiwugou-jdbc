package com.yiwugou.jdbc.core.filter;

/**
 * 
 * AbstractFilter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 上午10:17:56
 */
public abstract class AbstractFilter implements Filter {
    protected Invoker invoker;

    @Override
    public AbstractFilter setInvoker(Invoker invoker) {
        this.invoker = invoker;
        return this;
    }

}
