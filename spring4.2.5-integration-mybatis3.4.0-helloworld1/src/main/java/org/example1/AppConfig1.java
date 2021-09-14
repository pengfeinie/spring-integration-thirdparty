package org.example1;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan(basePackages= {"org.example1.mapper"})
@Import(MapperScannerRegistrar.class)
public class AppConfig1 {
	  
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
