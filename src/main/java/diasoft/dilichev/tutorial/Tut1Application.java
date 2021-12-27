package diasoft.dilichev.tutorial;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Tut1Application {

	public static void main(String[] args) {
		SpringApplication.run(Tut1Application.class, args);
	}
}
