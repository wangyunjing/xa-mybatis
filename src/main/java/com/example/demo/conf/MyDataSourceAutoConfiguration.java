package com.example.demo.conf;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
public class MyDataSourceAutoConfiguration {


	@Primary
	@Bean(name = "dataSource1")
	public DataSource dataSource1() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSource(xaDataSource1());
		return ds;
	}

	@Bean(name = "dataSource2")
	public DataSource dataSource2() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSource(xaDataSource2());
		return ds;
	}

	XADataSource xaDataSource1() {
		DruidXADataSource xaDataSource = new DruidXADataSource();
		xaDataSource.setUrl("jdbc:mysql://localhost:3306/jtm?useUnicode=true&characterEncoding=UTF8&useSSL=false");
		xaDataSource.setUsername("root");
		xaDataSource.setPassword("root");
		return xaDataSource;
	}

	XADataSource xaDataSource2() {
		DruidXADataSource xaDataSource = new DruidXADataSource();
		xaDataSource.setUrl("jdbc:mysql://localhost:3306/jtm1?useUnicode=true&characterEncoding=UTF8&useSSL=false");
		xaDataSource.setUsername("root");
		xaDataSource.setPassword("root");
		return xaDataSource;
	}

	@Bean("first")
	JdbcTemplate first(@Qualifier("dataSource1") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

	@Bean("second")
	JdbcTemplate second(@Qualifier("dataSource2") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

}
