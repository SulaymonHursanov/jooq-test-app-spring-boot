package com.semi.config;

import lombok.extern.slf4j.Slf4j;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class JooqConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSourceProxy () {
        return new TransactionAwareDataSourceProxy(dataSource());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager () {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider () {
        return new DataSourceConnectionProvider(transactionAwareDataSourceProxy());
    }

    @Bean
    public DefaultConfiguration configuration () {
        DefaultConfiguration jooqConfig = new DefaultConfiguration();
        jooqConfig.set(connectionProvider());
        jooqConfig.set(SQLDialect.POSTGRES);
        return jooqConfig;
    }

    @Bean
    public DefaultDSLContext dsl () {
        return new DefaultDSLContext(configuration());
    };
}
