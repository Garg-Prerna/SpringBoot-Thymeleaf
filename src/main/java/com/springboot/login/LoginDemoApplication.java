package com.springboot.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.login.model.Login;
import com.springboot.login.service.LoginService;

/**
 * @author Prerna Garg
 *
 */
@SpringBootApplication
public class LoginDemoApplication {

	private static LoginService loginService;

	/**
	 * @param loginService the loginService to set
	 */
	@Autowired
	public void setLoginService(LoginService loginService) {
		LoginDemoApplication.loginService = loginService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LoginDemoApplication.class, args);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Login login = new Login();
		login.setUsername("");
		login.setPassword(passwordEncoder.encode("test"));
		login.setRole("USER");
		loginService.saveLoginDetails(login);
	}

}
