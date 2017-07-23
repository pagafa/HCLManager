package com.gallegoFalcon.managerNfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EnableWebSecurity
	@Order(Ordered.HIGHEST_PRECEDENCE)
	class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().antMatchers("/resources/**", "/u/**", "/l/**").permitAll()
					.anyRequest().authenticated().and().httpBasic();
		}
	}
}