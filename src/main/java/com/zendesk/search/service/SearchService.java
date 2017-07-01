package com.zendesk.search.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.zendesk.search.model.Organization;
import com.zendesk.search.model.Ticket;
import com.zendesk.search.model.User;
import com.zendesk.search.repository.OrganizationRepository;
import com.zendesk.search.repository.TicketRepository;
import com.zendesk.search.repository.UserRepository;
import com.zendesk.search.util.Constants;

@Component
public class SearchService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	UserIndexBuilder userIndexBuilder;

	@Autowired
	TicketIndexBuilder ticketIndexBuilder;

	@Autowired
	OrganizationIndexBuilder organizationIndexBuilder;

	@Value("${users.file.path}")
	private String usersFilePath;

	@Value("${tickets.file.path}")
	private String ticketsFilePath;

	@Value("${organizations.file.path}")
	private String organizationsFilePath;

	public void buildIndexes() throws FileNotFoundException, IOException, ParseException {
		userIndexBuilder.buildIndex(new File(usersFilePath));
		ticketIndexBuilder.buildIndex(new File(ticketsFilePath));
		organizationIndexBuilder.buildIndex(new File(organizationsFilePath));

	}

	private String getMethodNameFromSearchTerm(String searchTerm) {

		return "findBy" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, searchTerm);

	}

	public String executeSearch(int repoType, String searchTerm, String searchValue) {
		String result = null;
		if (repoType == 1) {
			if (searchTerm.equals("_id")) {
				User user = userRepository.findOne(Long.parseLong(searchValue));
				result = (user != null) ? user.toString() : Constants.NOTHING_FOUND;
			}

			else {
				Object resultObj = executeTermSearch(userRepository, searchTerm, searchValue);
				result = (resultObj != null) ? ((List<User>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}
		if (repoType == 2) {
			if (searchTerm.equals("_id")) {
				Ticket ticket = ticketRepository.findOne(searchValue);
				result = (ticket != null) ? ticket.toString() : Constants.NOTHING_FOUND;
			}

			else {
				Object resultObj = executeTermSearch(ticketRepository, searchTerm, searchValue);
				result = (resultObj != null) ? ((List<Ticket>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}
		if (repoType == 3) {
			if (searchTerm.equals("_id")) {
				Organization organization = organizationRepository.findOne(Long.parseLong(searchValue));
				result = (organization != null) ? organization.toString() : Constants.NOTHING_FOUND;
			} else {
				Object resultObj = executeTermSearch(organizationRepository, searchTerm, searchValue);
				result = (resultObj != null) ? ((List<Organization>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}

		return result;
	}

	public Object executeTermSearch(Object obj, String searchTerm, String searchValue) {
		Object retObj = null;
		try {

			Method[] methods = obj.getClass().getMethods();
			for (Method m : methods) {
				if (m.getName().equalsIgnoreCase(getMethodNameFromSearchTerm(searchTerm))) {
					Class<?>[] pType = m.getParameterTypes();
					if (pType[0].toString().equals("boolean"))
						retObj = m.invoke(obj, Boolean.parseBoolean(searchValue));
					else if (pType[0].toString().equals("int"))
						retObj = m.invoke(obj, Integer.parseInt(searchValue));
					else if (pType[0].getName().equals("java.lang.Long"))
						retObj = m.invoke(obj, Long.parseLong(searchValue));

					else
						retObj = m.invoke(obj, pType[0].cast(searchValue));

					break;
				}
			}

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		return retObj;
	}

	public String listAll(int repo) {
		if (repo == 1)
			return ((List<User>) Lists.newArrayList(userRepository.findAll())).toString();
		if (repo == 2)
			return ((List<Ticket>) Lists.newArrayList(ticketRepository.findAll())).toString();
		if (repo == 3)
			return ((List<Organization>) Lists.newArrayList(organizationRepository.findAll())).toString();
		return "Not valid";
	}

	private void listFields(Class pojo, String name) {
		System.out.println("---------------------------\n\n");
		System.out.println("Search " + name + " with :");
		Field[] allFields = pojo.getDeclaredFields();
		for (Field field : allFields) {
			System.out.println("\t" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()));
		}

	}

	public void listSearchTerms() {
		listFields(User.class, "Users");
		listFields(Ticket.class, "Tickets");
		listFields(Organization.class, "Organization");

	}
}
