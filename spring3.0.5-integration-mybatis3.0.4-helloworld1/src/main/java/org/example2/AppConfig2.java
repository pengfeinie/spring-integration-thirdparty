package org.example2;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {
	
	  @Bean
	  public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
		  MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		  mapperScannerConfigurer.setBasePackage("org.example2.mapper");
		  mapperScannerConfigurer.setSqlSessionFactory(sqlSessionFactory().getObject());
		  return mapperScannerConfigurer;
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
