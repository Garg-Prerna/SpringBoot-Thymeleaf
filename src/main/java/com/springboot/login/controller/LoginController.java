package com.springboot.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.login.model.Login;

/**
 * @author Prerna Garg
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@GetMapping("/logout-success")
	public String logout() {
		return "logout-success";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}
