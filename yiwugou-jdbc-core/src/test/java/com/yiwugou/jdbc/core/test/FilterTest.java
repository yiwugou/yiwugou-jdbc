package com.yiwugou.jdbc.core.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yiwugou.jdbc.core.filter.ActiveFilter;
import com.yiwugou.jdbc.core.filter.Filter;
import com.yiwugou.jdbc.core.filter.Invoker;
import com.yiwugou.jdbc.core.filter.RetryFilter;
import com.yiwugou.jdbc.core.filter.TimeoutFilter;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

public class FilterTest {
    public static void main(String[] args) throws Throwable {
        new FilterTest().test1();
    }

    @Test
    public void test1() throws Throwable {
        Filter activeFilter = new ActiveFilter(1);
        Filter timeoutFilter = new TimeoutFilter(1000L);
        Filter retryFilter = new RetryFilter(2);
        List<Filter> filters = Arrays.asList(activeFilter, timeoutFilter);
        Invoker<String> invock = Filter.buildFilterChain(filters, new Invoker<String>() {
            @Override
            public String invoke(String sql, MasterSlaveRule masterSlaveRule) throws Exception {
                System.out.println("invock");
                return "invock";
            }
        });
        invock.invoke(null, null);
    }
}
