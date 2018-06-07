package com.yiwugou.jdbc.core.db;

import com.yiwugou.jdbc.core.adapter.AbstractDataSourceAdapter;
import com.yiwugou.jdbc.core.rule.MasterSlaveRule;

import lombok.Getter;

/**
 * 
 * MasterSlaveDataSource
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月8日 下午3:20:32
 */
@Getter
public class MasterSlaveDataSource extends AbstractDataSourceAdapter {

    private MasterSlaveRule masterSlaveRule;

    public MasterSlaveDataSource(MasterSlaveRule masterSlaveRule) {
        super(masterSlaveRule.getMasterDataSource());
        this.masterSlaveRule = masterSlaveRule;
    }

    @Override
    public MasterSlaveConnection getConnection() {
        return new MasterSlaveConnection(this);
    }

}
