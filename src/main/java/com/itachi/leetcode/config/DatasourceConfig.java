package com.itachi.leetcode.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xutigrong
 * @date : 2020/1/21
 */
@Configuration
public class DatasourceConfig {

    @Resource
    private DatabaseConfig databaseConfig;

    @Autowired
    private TableShardingAlgorithm tableShardingAlgorithm;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return this.buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {

        Map<String, DataSource> map = new HashMap<>();
        map.put(databaseConfig.getDatabaseName(), databaseConfig.createDataSource());

        DataSourceRule dataSourceRule = new DataSourceRule(map);

        TableRule orderTableRule = TableRule.builder("user")
                .actualTables(Arrays.asList("user_0", "user_1"))
                .dataSourceRule(dataSourceRule)
                .build();

        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Collections.singletonList(orderTableRule))
                .tableShardingStrategy(new TableShardingStrategy("sex",
                        (SingleKeyTableShardingAlgorithm<?>) tableShardingAlgorithm)).build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

}
