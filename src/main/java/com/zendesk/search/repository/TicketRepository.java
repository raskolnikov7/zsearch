package com.zendesk.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zendesk.search.model.Ticket;

public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {
	List<Ticket> findByType(String type);

	List<Ticket> findByUrl(String url);

	List<Ticket> findByExternalId(String externalId);

	List<Ticket> findBySubject(String subject);

	List<Ticket> findByCreatedAt(String createdAt);

	List<Ticket> findByHasIncidents(boolean hasIncidents);

	List<Ticket> findByDescription(String description);

	List<Ticket> findByPriority(String priority);

	List<Ticket> findByStatus(String status);

	List<Ticket> findByDueAt(String dueAt);

	List<Ticket> findByVia(String via);

	List<Ticket> findByTag(String tag);

	List<Ticket> findByOrganizationId(Long organizationId);

	List<Ticket> findBySubmitterId(Long submittterId);

	List<Ticket> findByAssigneeId(Long assigneeId);

}
