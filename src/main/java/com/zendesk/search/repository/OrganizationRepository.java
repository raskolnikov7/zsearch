package com.zendesk.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zendesk.search.model.Organization;

public interface OrganizationRepository extends ElasticsearchRepository<Organization, Long> {
	List<Organization> findByName(String name);

	List<Organization> findByDomainNames(String domainNames);

	List<Organization> findByDetails(String details);

	List<Organization> findByUrl(String url);

	List<Organization> findByExternalId(String externalId);

	List<Organization> findByCreatedAt(String createdAt);

	List<Organization> findByTag(String tag);

	List<Organization> findBySharedTickets(boolean sharedTickets);

}
