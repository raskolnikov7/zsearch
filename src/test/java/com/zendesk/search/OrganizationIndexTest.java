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

import com.zendesk.search.model.Organization;
import com.zendesk.search.repository.OrganizationRepository;
import com.zendesk.search.test.SearchTestApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchTestApp.class)
public class OrganizationIndexTest {

	@Autowired
	OrganizationRepository organizationTestRepository;

	@Before
	public void before() {
		organizationTestRepository.deleteAll();
	}

	@Test
	public void testSaveOrganization() {

		Organization org = new Organization();
		org.setId((long) 123);
		org.setName("Ethanze");

		Organization testOrg = organizationTestRepository.save(org);

		assertNotNull(org.getId());
		assertEquals(testOrg.getName(), org.getName());

	}

	@Test
	public void testFindOne() {

		Organization org = new Organization();
		org.setId((long) 100);
		org.setName("Ethanze");
		organizationTestRepository.save(org);
		Organization testOrg = organizationTestRepository.findOne(org.getId());

		assertNotNull(testOrg.getId());
		assertEquals(testOrg.getName(), org.getName());

	}

	@Test
	public void testFindByName() {

		Organization org = new Organization();
		org.setId((long) 100);
		org.setName("Ethanze");
		organizationTestRepository.save(org);

		List<Organization> orgs = organizationTestRepository.findByName(org.getName());
		assertEquals(orgs.size(), 1);
	}

}
