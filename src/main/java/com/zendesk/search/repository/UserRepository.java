package com.zendesk.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zendesk.search.model.User;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
	List<User> findByName(String name);

	List<User> findByUrl(String url);

	List<User> findByExternalId(String externalId);

	List<User> findByAlias(String alias);

	List<User> findByCreatedAt(String createdAt);

	List<User> findByActive(boolean active);

	List<User> findByVerified(boolean verified);

	List<User> findByShared(boolean shared);

	List<User> findByLocale(String locale);

	List<User> findByTimezone(String timezone);

	List<User> findByLastLoginAt(String lastLoginAt);

	List<User> findByEmail(String email);

	List<User> findByPhone(String phone);

	List<User> findBySignature(String signature);

	List<User> findByTags(String tags);

	List<User> findByRole(String role);

	List<User> findByOrganizationId(Long organizationId);
}
