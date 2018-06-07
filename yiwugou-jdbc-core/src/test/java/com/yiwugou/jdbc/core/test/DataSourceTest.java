package com.yiwugou.jdbc.core.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.yiwugou.jdbc.core.db.MasterSlaveDataSource;
import com.yiwugou.jdbc.core.filter.Filter;
import com.yiwugou.jdbc.core.filter.MockFilter;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

/**
 * 
 * DataSourceTest
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月3日 上午11:25:18
 */
public class DataSourceTest {
    public static void main(String[] args) throws Exception {
        JdbcTemplate jdbcTemplate = initJdbcTemplate(initMasterSlaveDataSource());

        for (int i = 0; i <= 100000; i++) {
            try {
                // List<Map<String, Object>> objs = jdbcTemplate.queryForList("select * from T_DEMO");
                jdbcTemplate.update("insert into T_DEMO (name) values ('abcd" + i + "')");
                System.err.println("execute=" + i);
                // System.err.println(i + "=" + objs);
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testReplication() {
        DataSource dataSource = initDataSource("com.mysql.jdbc.ReplicationDriver",
                "jdbc:mysql:replication://master,slave1,slave2,slave3/test", "root", "admin123");
        JdbcTemplate jdbcTemplate = initJdbcTemplate(dataSource);
    }

    public static DataSource initDataSource(String driver, String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(100);
        dataSource.setMinIdle(5);

        dataSource.setFailFast(true); //重要 不然会卡住
        //        dataSource.setBreakAfterAcquireFailure(true);
        //        dataSource.setAsyncInit(true);
        return dataSource;
    }

    public static MasterSlaveRule initMasterSlaveRule() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        DataSource masterDataSource = initDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://10.6.2.72:3306/demo",
                "root", "admin123");
        DataSource slaveDataSource1 = initDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/demo",
                "root", "");

        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave1", slaveDataSource1);
        //        dataSourceMap.put("slave2", masterDataSource);

        MasterSlaveRule masterSlaveRule = new MasterSlaveRule(dataSourceMap);
        masterSlaveRule.setMasterDataSourceName("slave1");
        masterSlaveRule.setSlaveDataSourceNames("master");
        List<Filter> filters = initFilters();
        masterSlaveRule.setFilters(filters);
        return masterSlaveRule;
    }

    private static List<Filter> initFilters() {
        // Filter retry = new RetryFilter(2);
        // Filter timeout = new TimeoutFilter(3000L);
        Filter mock = new MockFilter();
        List<Filter> filters = Arrays.asList(mock);
        return null;
    }

    public static DataSource initMasterSlaveDataSource() {
        MasterSlaveRule masterSlaveRule = initMasterSlaveRule();
        MasterSlaveDataSource masterSlaveDataSource = new MasterSlaveDataSource(masterSlaveRule);
        return masterSlaveDataSource;
    }

    public static JdbcTemplate initJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
