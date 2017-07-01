package com.zendesk.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zendesk.search.model.User;
import com.zendesk.search.repository.UserRepository;
import com.zendesk.search.test.SearchTestApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchTestApp.class)
public class UsersIndexTest {

	@Autowired
	UserRepository userTestRepository;

	@Before
	public void before() {
		userTestRepository.deleteAll();
	}

	@Test
	public void testSaveUser() {

		User user = new User();
		user.setId((long) 1001);
		user.setName("Boris Baker");

		User testUser = userTestRepository.save(user);

		assertNotNull(user.getId());
		assertEquals(testUser.getName(), user.getName());

	}

	@Test
	public void testFindOne() {

		User user = new User();
		user.setId((long) 1002);
		user.setName("Diego Maradona");

		userTestRepository.save(user);
		User testUser = userTestRepository.findOne(user.getId());

		assertNotNull(testUser.getId());
		assertEquals(testUser.getName(), user.getName());

	}

	@Test
	public void testFindByName() {

		User user = new User();
		user.setId((long) 1003);
		user.setName("Sachin Tendulkar");
		userTestRepository.save(user);

		List<User> users = userTestRepository.findByName(user.getName());
		assertEquals(users.size(), 1);
	}

}
