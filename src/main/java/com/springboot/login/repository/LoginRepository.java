package com.springboot.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.login.model.Login;

/**
 * @author Prerna Garg
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

}
