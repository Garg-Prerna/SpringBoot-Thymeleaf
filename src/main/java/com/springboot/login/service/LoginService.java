package com.springboot.login.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.login.model.Login;
import com.springboot.login.repository.LoginRepository;

/**
 * @author Prerna Garg
 *
 */
@Service
public class LoginService implements UserDetailsService {

	private LoginRepository loginRepository;

	/**
	 * @param loginRepository the loginRepository to set
	 */
	@Autowired
	public void setLoginRepository(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public void saveLoginDetails(Login login) {
		loginRepository.save(login);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> login = loginRepository.findById(username);
		if (login.isPresent()) {
			Login user = login.get();
			List<String> roles = new ArrayList<>();
			roles.add(user.getRole());
			return new User(user.getUsername(), user.getPassword(), mapRoles(roles));
		}
		throw new UsernameNotFoundException("Invalid username or password");
	}

	private Collection<? extends GrantedAuthority> mapRoles(Collection<String> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}
