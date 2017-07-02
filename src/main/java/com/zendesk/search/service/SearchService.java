package com.zendesk.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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

	public UserRepository getUserRepository() {
		return userRepository;
	}

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

	@Autowired
	ResourceLoader resourceLoader;

	public void buildIndexes() throws FileNotFoundException, IOException, ParseException {

		userIndexBuilder.buildIndex(resourceLoader.getResource("users.json").getInputStream());
		ticketIndexBuilder.buildIndex(resourceLoader.getResource("tickets.json").getInputStream());
		organizationIndexBuilder.buildIndex(resourceLoader.getResource("organizations.json").getInputStream());

	}

	private String getMethodNameFromSearchTerm(String searchTerm) {

		return "findBy" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, searchTerm);

	}

	public String executeSearch(int repoType, String searchTerm, String searchValue) {
		String result = null;

		if (repoType == 1) {
			if (!listFields(User.class).contains(searchTerm)) {
				printFields(listFields(User.class), "Users");
				return Constants.NOTHING_FOUND;
			}
			if (searchTerm.equals("_id")) {
				User user = userRepository.findOne(Long.parseLong(searchValue));
				result = (user != null) ? user.toString() : Constants.NOTHING_FOUND;
			}

			else {
				Object resultObj = executeTermSearch(userRepository, searchTerm, searchValue);
				result = (resultObj != null && !((List<User>) resultObj).isEmpty())
						? ((List<User>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}
		if (repoType == 2) {
			if (!listFields(Ticket.class).contains(searchTerm)) {
				printFields(listFields(Ticket.class), "Tickets");
				return Constants.NOTHING_FOUND;
			}

			if (searchTerm.equals("_id")) {
				Ticket ticket = ticketRepository.findOne(searchValue);
				result = (ticket != null) ? ticket.toString() : Constants.NOTHING_FOUND;
			}

			else {
				Object resultObj = executeTermSearch(ticketRepository, searchTerm, searchValue);
				result = (resultObj != null && !((List<Ticket>) resultObj).isEmpty())
						? ((List<Ticket>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}
		if (repoType == 3) {
			if (!listFields(Organization.class).contains(searchTerm)) {
				printFields(listFields(Organization.class), "Organizations");
				return Constants.NOTHING_FOUND;
			}

			if (searchTerm.equals("_id")) {
				Organization organization = organizationRepository.findOne(Long.parseLong(searchValue));
				result = (organization != null) ? organization.toString() : Constants.NOTHING_FOUND;
			} else {
				Object resultObj = executeTermSearch(organizationRepository, searchTerm, searchValue);
				result = (resultObj != null && !((List<Organization>) resultObj).isEmpty())
						? ((List<Organization>) resultObj).toString() : Constants.NOTHING_FOUND;
			}
		}

		return result;
	}

	/**
	 * Dynamically load search method via reflection based on search term and
	 * execute search
	 * 
	 * @param obj
	 * @param searchTerm
	 * @param searchValue
	 * @return
	 */
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
		return Constants.NOTHING_FOUND;
	}

	private List<String> listFields(Class pojo) {
		System.out.println(Constants.LINE);
		List<String> fieldNames = new ArrayList<String>();
		Field[] allFields = pojo.getDeclaredFields();
		for (Field field : allFields) {
			if (field.getName().equals("id"))
				fieldNames.add("_id");
			else
				fieldNames.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()));
		}
		return fieldNames;
	}

	private void printFields(List<String> fields, String name) {
		System.out.println("Search " + name + " with :");

		for (String field : fields) {
			System.out.println("\t" + field);
		}
	}

	public void listSearchTerms() {
		printFields(listFields(User.class), "Users");
		printFields(listFields(Ticket.class), "Tickets");
		printFields(listFields(Organization.class), "Organizations");

	}
}
