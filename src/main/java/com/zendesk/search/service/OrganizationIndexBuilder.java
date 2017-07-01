package com.zendesk.search.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zendesk.search.model.Organization;
import com.zendesk.search.repository.OrganizationRepository;

@Component
public class OrganizationIndexBuilder implements IndexBuilder {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public void buildIndex(File file) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));
		for (Object o : jsonArray) {
			Organization organization = new Organization();
			JSONObject jsonUser = (JSONObject) o;
			organization.setId((Long) jsonUser.get("_id"));
			organization.setUrl((String) jsonUser.get("url"));

			organization.setExternalId((String) jsonUser.get("external_id"));
			organization.setName((String) jsonUser.get("name"));
			JSONArray domainNames = (JSONArray) jsonUser.get("domain_names");

			organization.setDomainNames(domainNames.toString());

			organization.setCreatedAt((String) jsonUser.get("created_at"));

			organization.setSharedTickets((boolean) jsonUser.get("shared_tickets"));
			organization.setDetails((String) jsonUser.get("details"));

			JSONArray tags = (JSONArray) jsonUser.get("tags");

			organization.setTag(tags.toString());
			organizationRepository.save(organization);
		}

		System.out.println("ORGANIZATIONS ===============> " + organizationRepository.count());

	}

}
