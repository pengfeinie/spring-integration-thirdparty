package org.example1;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {
	
	  @Bean
	  public MapperFactoryBean<UserMapper1> userMapper() throws Exception {
	    MapperFactoryBean<UserMapper1> factoryBean = new MapperFactoryBean<UserMapper1>();
	    factoryBean.setMapperInterface(UserMapper1.class);
	    factoryBean.setSqlSessionFactory(sqlSessionFactory().getObject());
	    return factoryBean;
	  }
	  
	  @Bean
	  public SqlSessionFactoryBean sqlSessionFactory() {
		  SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		  sqlSessionFactoryBean.setDataSource(dataSource());
		  return sqlSessionFactoryBean;
	  }
	  
	  @Bean
	  public DataSource dataSource() {
		  return JdbcConnectionPool.create("","","");
	  }
	

}
