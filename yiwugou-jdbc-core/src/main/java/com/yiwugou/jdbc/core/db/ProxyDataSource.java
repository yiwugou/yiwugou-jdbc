package com.yiwugou.jdbc.core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.yiwugou.jdbc.core.adapter.AbstractDataSourceAdapter;
import com.yiwugou.jdbc.core.ping.Ping;
import com.yiwugou.jdbc.core.ping.PingFactory;

import lombok.Getter;

/**
 * 
 * ProxyDataSource
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:19:35
 */

public class ProxyDataSource extends AbstractDataSourceAdapter {
    @Getter
    private String name;
    @Getter
    private DataSource dataSource;
    @Getter
    private boolean alived;

    private Ping ping;

    private int retry;

    protected final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(2);

    public ProxyDataSource(String name, DataSource dataSource) {
        super(dataSource);
        this.name = name;
        this.dataSource = dataSource;
        this.alived = true;
        this.ping = PingFactory.create(this.getDbType());
    }

    public void setup() {
        this.alived = true;
        this.retry = 0;
    }

    public void shutdown() {
        this.alived = false;
        loopPing();
    }

    private void loopPing() {
        long delay = (long) (Math.pow(1.5, this.retry++) * 10);
        if (delay > 3600) {
            delay = 3600;
        }
        this.EXECUTOR.schedule(() -> {
            boolean isAlive = this.ping.isAlive(this);
            if (isAlive) {
                this.setup();
            } else {
                this.loopPing();
            }
        }, delay, TimeUnit.SECONDS);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
