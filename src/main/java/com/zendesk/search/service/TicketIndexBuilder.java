package com.zendesk.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zendesk.search.model.Ticket;
import com.zendesk.search.repository.TicketRepository;

@Component
public class TicketIndexBuilder implements IndexBuilder {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void buildIndex(InputStream file) throws FileNotFoundException, IOException, ParseException {
		ticketRepository.deleteAll();
		JSONParser parser = new JSONParser();

		JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(file));
		List<Ticket> tickets = new ArrayList();
		for (Object o : jsonArray) {
			Ticket ticket = new Ticket();
			JSONObject jsonUser = (JSONObject) o;
			ticket.setId((String) jsonUser.get("_id"));
			ticket.setUrl((String) jsonUser.get("url"));

			ticket.setExternalId((String) jsonUser.get("external_id"));
			ticket.setType((String) jsonUser.get("type"));

			ticket.setSubject((String) jsonUser.get("subject"));
			ticket.setCreatedAt((String) jsonUser.get("created_at"));

			ticket.setHasIncidents((boolean) jsonUser.get("has_incidents"));
			ticket.setDescription((String) jsonUser.get("description"));
			ticket.setStatus((String) jsonUser.get("status"));
			ticket.setPriority((String) jsonUser.get("priority"));
			ticket.setDueAt((String) jsonUser.get("due_at"));
			ticket.setVia((String) jsonUser.get("via"));

			ticket.setOrganizationId((Long) jsonUser.get("organization_id"));
			ticket.setSubmitterId((Long) jsonUser.get("submitter_id"));
			ticket.setAssigneeId((Long) jsonUser.get("assignee_id"));

			JSONArray tags = (JSONArray) jsonUser.get("tags");

			ticket.setTag(tags.toString());
			tickets.add(ticket);
		}
		ticketRepository.save(tickets);
		System.out.println("TICKETS ===============> " + ticketRepository.count());

	}
}
