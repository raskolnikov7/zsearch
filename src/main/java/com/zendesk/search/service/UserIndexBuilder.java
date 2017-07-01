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

import com.zendesk.search.model.User;
import com.zendesk.search.repository.UserRepository;

@Component
public class UserIndexBuilder implements IndexBuilder {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void buildIndex(File file) throws FileNotFoundException, IOException, ParseException {
		userRepository.deleteAll();
		JSONParser parser = new JSONParser();

		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));
		// List<User> users = new ArrayList<User>();
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

			user.setTag(tags.toString());
			user.setSuspended((boolean) jsonUser.get("suspended"));
			user.setRole((String) jsonUser.get("role"));
			userRepository.save(user);
			// users.add(user);
		}

		// userRepository.save(users);
		// User aUser = userRepository.findOne((long) 1);

		System.out.println("USERS ===============> " + userRepository.count());

	}

}
