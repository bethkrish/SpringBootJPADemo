package com.vijayforvictory.demo.jpa.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vijayforvictory.demo.jpa.repo.User;
import com.vijayforvictory.demo.jpa.repo.UsersRepository;
import com.vijayforvictory.demo.jpa.service.UserService;

/**
 * JUnit 5 Test Class to test JPA Repository
 * 
 * @author vijay
 *
 */
@ExtendWith(SpringExtension.class)
public class TestUserRepository {

	@InjectMocks
	UserService userService;

	@MockBean
	UsersRepository userRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@BeforeEach
	void setupTest() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Tests JPA Repository. Mock JPA Repository to return hoard-coded Users list.
	 * InjectMock the service and make call to repository methods. Assert results
	 * 
	 * @throws Exception
	 */
	@Test
	public void testControllerAndMockRepository() throws Exception {
		User user1 = new User();
		user1.setFirstName("Vijay");

		User user2 = new User();
		user2.setFirstName("SuperStar");

		User user3 = new User();
		user3.setFirstName("Rajini");

		if (logger.isDebugEnabled()) {
			logger.debug("userRespository " + userRepository);
		}
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2, user3));

		if (logger.isDebugEnabled()) {
			logger.debug("Mocking Service Call userService.getUsers()" + userRepository.findAll());
		}
		List<User> lstUsers = userService.getUsers();
		assertNotNull(lstUsers);
		assertEquals(3, lstUsers.size());
	}

}
