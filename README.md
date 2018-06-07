# 数据库主从切换 一主多从
## 暂时实现的切换方法

1. master 正常时 所有请求router到master;
2. master不正常时，DQL路由到slaves(slaves 会进行负载均衡,算法自定),DML DDL等抛异常;

## 配置方法
### java （注：dataSource 支持任意的DataSource）

```

public static DataSource initMasterSlaveDataSource() {
    MasterSlaveRule masterSlaveRule = initMasterSlaveRule();
    MasterSlaveDataSource masterSlaveDataSource = new MasterSlaveDataSource(masterSlaveRule);
    return masterSlaveDataSource;
}

public static MasterSlaveRule initMasterSlaveRule() {
    Map<String, DataSource> dataSourceMap = new HashMap<>();
    DataSource masterDataSource = initDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://10.6.2.72:3306/demo",
            "root", "admin123");
    DataSource slaveDataSource1 = initDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/demo",
            "root", "");

    dataSourceMap.put("master", masterDataSource);
    dataSourceMap.put("slave1", slaveDataSource1);
    dataSourceMap.put("slave2", masterDataSource);

    MasterSlaveRule masterSlaveRule = new MasterSlaveRule(dataSourceMap);
    masterSlaveRule.setMasterDataSourceName("master");
    masterSlaveRule.setSlaveDataSourceNames("slave1", "slave2");
    return masterSlaveRule;
}

/**
 * 支持任意的DataSource 这里使用Druid做测试
 */
public static DataSource initDataSource(String driver, String url, String username, String password) {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setMaxActive(100);
    dataSource.setMinIdle(5);
    
    //使用Druid时候 这句非常重要 不然数据库down掉，会一直等待链接恢复；使用其他的dataSource时，参考相关的配置
    dataSource.setFailFast(true); 

    return dataSource;
}
```

### spring 参考java代码的配置即可

## 测试情况
### 未进行完整的详细的测试 可能有些sql不支持

## Future
### Sql的读写分离 详细的测试计划