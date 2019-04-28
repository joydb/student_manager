package com.dbstudy.springboot.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringJDBC {
    @Autowired
    private DataSource dataSource;
    @Bean(name = "myJdbcTemplate")
    @Primary
    public JdbcTemplate getJdbcTemplate()
    {
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(this.dataSource);
        return jdbcTemplate;
    }
}
