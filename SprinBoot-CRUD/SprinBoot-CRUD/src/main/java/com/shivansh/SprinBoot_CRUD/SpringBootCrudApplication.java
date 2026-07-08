package com.shivansh.SprinBoot_CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import javax.sql.DataSource;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class SpringBootCrudApplication {
	public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudApplication.class, args);

	}

}
