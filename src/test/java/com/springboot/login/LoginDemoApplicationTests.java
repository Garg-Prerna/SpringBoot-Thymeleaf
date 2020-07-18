package com.springboot.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Prerna Garg
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LoginDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		if (null == mvc) {
			mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		}
	}

	@Test
	void testUserLogin() throws Exception {
		MvcResult mvcResult = mvc.perform(get("/user/login")).andExpect(view().name("login"))
				.andExpect(model().attributeExists("login")).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	void testWelcomeSuccess() throws Exception {
		MvcResult mvcResult = mvc.perform(get("/user/welcome").with(user("test").password("pass")))
				.andExpect(view().name("welcome")).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test()
	void testWelcomeFailure() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> mvc.perform(get("/user/welcome").with(user("").password(""))));
	}

}
