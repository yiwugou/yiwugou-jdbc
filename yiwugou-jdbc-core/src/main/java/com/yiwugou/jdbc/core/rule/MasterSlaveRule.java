
package com.yiwugou.jdbc.core.rule;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.yiwugou.jdbc.core.algorithm.LoadBalance;
import com.yiwugou.jdbc.core.algorithm.RoundRobinLoadBalance;
import com.yiwugou.jdbc.core.constant.SqlType;
import com.yiwugou.jdbc.core.db.ProxyDataSource;
import com.yiwugou.jdbc.core.filter.Filter;
import com.yiwugou.jdbc.core.parsing.DruidSqlParser;
import com.yiwugou.jdbc.core.parsing.SqlParser;
import com.yiwugou.jdbc.core.util.AssertUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * MasterSlaveRule
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:21:05
 */
@Accessors(chain = true)
public class MasterSlaveRule {

    private final Map<String, DataSource> dataSourceMap;

    @Getter
    private ProxyDataSource masterDataSource;

    private List<ProxyDataSource> slaveDataSources = new LinkedList<>();

    @Setter
    @Getter
    private LoadBalance loadBalance = new RoundRobinLoadBalance();

    @Setter
    @Getter
    private SqlParser sqlParser = new DruidSqlParser();

    @Setter
    @Getter
    private List<Filter> filters;

    public MasterSlaveRule(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    public MasterSlaveRule setMasterDataSourceName(String masterDataSourceName) {
        DataSource dataSource = this.checkDataSource(masterDataSourceName);
        this.masterDataSource = new ProxyDataSource(masterDataSourceName, dataSource);
        return this;
    }

    public MasterSlaveRule setSlaveDataSourceNames(String... slaveDataSourceNames) {
        for (String slaveDataSourceName : slaveDataSourceNames) {
            DataSource dataSource = this.checkDataSource(slaveDataSourceName);
            this.slaveDataSources.add(new ProxyDataSource(slaveDataSourceName, dataSource));
        }
        return this;
    }

    private DataSource checkDataSource(String dataSourceName) {
        DataSource dataSource = dataSourceMap.get(dataSourceName);
        AssertUtils.notNull(dataSource, "Have no dataSource named " + dataSourceName);
        return dataSource;
    }

    public ProxyDataSource router(String sql) {
        SqlType sqlType = this.sqlParser.sqlType(sql);
        return this.loadBalance.select(sqlType, masterDataSource, slaveDataSources);
    }
}
