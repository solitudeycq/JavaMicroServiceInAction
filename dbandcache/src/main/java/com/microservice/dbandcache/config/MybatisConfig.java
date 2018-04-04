package com.microservice.dbandcache.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.microservice.dbandcache.mapper")
public class MybatisConfig {
    @Autowired
    private Environment env;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setTypeAliasesPackage("com.microservice.dbandcache.model");
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        return fb.getObject();
    }

    @Bean
    public DataSource microservicedb1DataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName",env.getProperty("microservicedb1.jdbc.driverClassName"));
        props.put("url",env.getProperty("microservicedb1.jdbc.url"));
        props.put("username",env.getProperty("microservicedb1.jdbc.username"));
        props.put("password",env.getProperty("microservicedb1.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource microservicedb2DataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName",env.getProperty("microservicedb2.jdbc.driverClassName"));
        props.put("url",env.getProperty("microservicedb2.jdbc.url"));
        props.put("username",env.getProperty("microservicedb2.jdbc.username"));
        props.put("password",env.getProperty("microservicedb2.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("microservicedb1DataSource")DataSource microservicedb1DataSource,@Qualifier("microservicedb2DataSource")DataSource microservicedb2DataSource){
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DatabaseType.microservicedb1,microservicedb1DataSource);
        targetDataSource.put(DatabaseType.microservicedb2,microservicedb2DataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(microservicedb1DataSource);
        return dataSource;
    }
}
