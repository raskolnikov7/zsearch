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

import com.zendesk.search.model.Ticket;
import com.zendesk.search.repository.TicketRepository;
import com.zendesk.search.test.SearchTestApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchTestApp.class)
public class TicketIndexTest {

	@Autowired
	TicketRepository ticketTestRepository;

	@Before
	public void before() {
		ticketTestRepository.deleteAll();
	}

	@Test
	public void testSaveTicket() {

		Ticket ticket = new Ticket();
		ticket.setId("abcd");
		ticket.setType("urgent");

		Ticket testTicket = ticketTestRepository.save(ticket);

		assertNotNull(ticket.getId());
		assertEquals(testTicket.getType(), ticket.getType());

	}

	@Test
	public void testFindOne() {

		Ticket ticket = new Ticket();
		ticket.setId("xyz");
		ticket.setDescription("some description");

		ticketTestRepository.save(ticket);
		Ticket testTicket = ticketTestRepository.findOne(ticket.getId());

		assertNotNull(testTicket.getId());
		assertEquals(testTicket.getDescription(), ticket.getDescription());

	}

	@Test
	public void testFindByPriority() {

		Ticket ticket = new Ticket();
		ticket.setId("123");
		ticket.setPriority("low");
		ticketTestRepository.save(ticket);

		List<Ticket> tickets = ticketTestRepository.findByPriority(ticket.getPriority());
		assertEquals(tickets.size(), 1);
	}

}
