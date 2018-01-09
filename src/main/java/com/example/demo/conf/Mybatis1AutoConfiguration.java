package com.example.demo.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 描述 TODO
 *
 * @author wang yun jing
 * @since TODO
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper1",
		sqlSessionFactoryRef = "sqlSessionFactory1",
		sqlSessionTemplateRef = "sqlSessionTemplate1")
public class Mybatis1AutoConfiguration {

	@Autowired
	@Qualifier("dataSource1")
	private DataSource dataSource;

	@Bean
	SqlSessionFactory sqlSessionFactory1() {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources(
					"classpath:mybatis-mapping-1/*.xml"));
			return sqlSessionFactoryBean.getObject();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
		return template;
	}

}
