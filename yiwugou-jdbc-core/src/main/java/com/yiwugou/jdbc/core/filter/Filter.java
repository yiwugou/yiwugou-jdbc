package com.yiwugou.jdbc.core.filter;

import java.util.List;

/**
 * 
 * Filter
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月9日 上午9:56:54
 */
public interface Filter extends Invoker {

    public Filter setInvoker(Invoker invoker);

    static <T> Invoker<T> buildFilterChain(List<Filter> filters, Invoker<T> invoker) {
        if (filters == null || filters.size() == 0 || filters.get(0) == null) {
            return invoker;
        }
        for (int i = 0, size = filters.size(); i < size; i++) {
            Filter filter = filters.get(i);
            if (i == size - 1) {
                filter.setInvoker(invoker);
            } else {
                filter.setInvoker(filters.get(i + 1));
            }
        }
        return filters.get(0);
    }

}
