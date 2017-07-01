package com.zendesk.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	}

	/*
	 * "_id": 1, "url": "http://initech.zendesk.com/api/v2/users/1.json",
	 * "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f", "name":
	 * "Francisca Rasmussen", "alias": "Miss Coffey", "created_at":
	 * "2016-04-15T05:19:46 -10:00", "active": true, "verified": true, "shared":
	 * false, "locale": "en-AU", "timezone": "Sri Lanka", "last_login_at":
	 * "2013-08-04T01:03:27 -10:00", "email": "coffeyrasmussen@flotonic.com",
	 * "phone": "8335-422-718", "signature": "Don't Worry Be Happy!",
	 * "organization_id": 119, "tags": [ "Springville", "Sutton",
	 * "Hartsville Hartley", "Diaperville" ], "suspended": true, "role": "admin"
	 */
	@Test
	public void testSaveUser() {

		User user = new User();
		user.setId((long) 1001);
		user.setUrl("http://initech.zendesk.com/api/v2/users/1001.json");
		user.setExternalId("74341f74-9c79-49d5-9611-87ef9b6eb75f");
		user.setName("Boris Baker");

		User testUser = userTestRepository.save(user);

		assertNotNull(user.getId());
		assertEquals(testUser.getName(), user.getName());

	}

}
