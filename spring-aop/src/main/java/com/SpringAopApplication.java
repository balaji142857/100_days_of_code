package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.conditions.DatabaseType;
import com.conditions.MongoDao;
import com.conditions.MyDao;
import com.conditions.MySqlDao;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}
	
	
//	@Bean
//	@Conditional(MySqlConditional.class)
//	public MyDao getMySqlDao() {
//		return new MySqlDao();
//	}
//	
//	@Bean
//	@Conditional(MongoConditional.class)
//	public MyDao getMongoDao() {
//		return new MongoDao();
//	}
	
	@Bean
	@DatabaseType("MySql")
	public MyDao getMySqlDao() {
		return new MySqlDao();
	}
	
	@Bean
	@DatabaseType("Mongo")
	public MyDao getMongoDao() {
		return new MongoDao();
	}

}
