package com.zendesk.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zendesk.search.model.User;
import com.zendesk.search.repository.UserRepository;

@Component
public class UserIndexBuilder implements IndexBuilder {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void buildIndex(InputStream fileInputStream) throws FileNotFoundException, IOException, ParseException {
		userRepository.deleteAll();
		JSONParser parser = new JSONParser();

		JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(fileInputStream));
		for (Object o : jsonArray) {
			User user = new User();
			JSONObject jsonUser = (JSONObject) o;
			user.setId((Long) jsonUser.get("_id"));
			user.setUrl((String) jsonUser.get("url"));

			user.setExternalId((String) jsonUser.get("external_id"));
			user.setName((String) jsonUser.get("name"));

			user.setAlias((String) jsonUser.get("alias"));
			user.setCreatedAt((String) jsonUser.get("created_at"));

			user.setActive((boolean) jsonUser.get("active"));

			if (jsonUser.get("verified") != null)
				user.setVerified((boolean) jsonUser.get("verified"));
			else
				user.setVerified(false);

			user.setShared((boolean) jsonUser.get("shared"));
			user.setLocale((String) jsonUser.get("locale"));
			user.setTimezone((String) jsonUser.get("timezone"));
			user.setLastLoginAt((String) jsonUser.get("last_login_at"));
			user.setEmail((String) jsonUser.get("email"));
			user.setPhone((String) jsonUser.get("phone"));

			user.setSignature((String) jsonUser.get("signature"));

			user.setOrganizationId((Long) jsonUser.get("organization_id"));

			JSONArray tags = (JSONArray) jsonUser.get("tags");

			user.setTags(tags.toString());
			user.setSuspended((boolean) jsonUser.get("suspended"));
			user.setRole((String) jsonUser.get("role"));
			userRepository.save(user);
		}

		System.out.println("USERS ===============> " + userRepository.count());

	}

}
