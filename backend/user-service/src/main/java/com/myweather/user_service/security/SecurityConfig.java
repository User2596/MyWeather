package com.myweather.user_service.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myweather.user_service.filter.JwtAuthFilter;


@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
//		.and()
//        .withUser("user").password(passwordEncoder().encode("userpass")).roles("USER");
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf()
		.disable()
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/register", "/login").permitAll()
		.anyRequest().authenticated()
		.and()
        .addFilterBefore(new JwtAuthFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class);
	}	
}