package com.samir.studentmgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.samir.studentmgmt.service.UserDetailsServiceImpl;

public class WebSecurityConfig extends WebSecurityConfiguration{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	 public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
	}
	
	  protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/","/student/save","/student/addStudent","/student/403").hasAnyAuthority("USER","ADMIN")
	            .antMatchers("/student/updateStudent","/student/delete").hasAuthority("ADMIN")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/login").permitAll()
	            .and()
	            .exceptionHandling().accessDeniedPage("/student/403")
	            .and()
	            .cors().and().csrf().disable();
	    }
	

}
