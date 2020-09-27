package com.vijayforvictory.demo.jpa.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.vijayforvictory.demo.jpa.repo.User;

/**
 * JUnit 5 Test Class - Tests User Services - Uses Mock MVC to launch Spring
 * Application Context - Use mock beans where needed
 * 
 * @author vijay
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TestUserService {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;


	private static Logger logger = LoggerFactory.getLogger(TestUserService.class);

	@BeforeEach
	public void setupTest() {

	}

	/**
	 * Test User Services by mocking it. Use MockMVC to launch application context
	 * and invoke API end-points. Assert results.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testControllerAndMockService() throws Exception {
		User user1 = new User();
		user1.setFirstName("Vijay");
		User user2 = new User();
		user2.setFirstName("Rajini");
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);


		// Mocking UserSerivce to return a hard-coded User object all the time
		when(userService.getUsers()).thenReturn(Arrays.asList(user1, user2));

		// when(userRepository.findAll()).thenReturn(Collections.emptyList());

		logger.debug("Mocking Service Call userService.getUsers()" + userService.getUsers());

		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

		// logger.debug("MvcResult is " + mvcResult.getResponse());

		// verify(userRepository).findAll();
	}

}
