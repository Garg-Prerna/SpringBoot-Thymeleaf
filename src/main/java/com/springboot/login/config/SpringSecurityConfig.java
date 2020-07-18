package com.springboot.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springboot.login.service.LoginService;

/**
 * @author Prerna Garg
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginService userService;

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/login", "/user/logout", "/user/welcome").authenticated().and()
				.authorizeRequests().antMatchers("/resources/**").permitAll().and().formLogin().loginPage("/user/login")
				.defaultSuccessUrl("/user/welcome").permitAll().and().logout().deleteCookies("remove")
				.invalidateHttpSession(true).logoutUrl("/user/logout").logoutSuccessUrl("/user/logout-success")
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).and().exceptionHandling()
				.accessDeniedPage("/user/login");

	}

}
