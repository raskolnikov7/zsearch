package com.zendesk.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.zendesk.search.util.Constants;

@Document(indexName = "organizations", type = "organization", shards = 1, replicas = 0, refreshInterval = "-1")

public class Organization {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomainNames() {
		return domainNames;
	}

	public void setDomainNames(String domainNames) {
		this.domainNames = domainNames;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isSharedTickets() {
		return sharedTickets;
	}

	public void setSharedTickets(boolean sharedTickets) {
		this.sharedTickets = sharedTickets;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	private String url;
	private String externalId;
	private String name;
	private String domainNames;
	private String createdAt;
	private String details;
	private boolean sharedTickets;
	private String tag;

	@Override
	public String toString() {
		return prettyText();
	}

	private String prettyText() {
		StringBuffer printStr = new StringBuffer();
		printStr.append(Constants.LINE);
		printStr.append(String.format(Constants.LONG_FORMAT, "_id", id));
		printStr.append(String.format(Constants.STR_FORMAT, "url", url));
		printStr.append(String.format(Constants.STR_FORMAT, "external_id", externalId));
		printStr.append(String.format(Constants.STR_FORMAT, "name", name));
		printStr.append(String.format(Constants.STR_FORMAT, "domain_names", domainNames));
		printStr.append(String.format(Constants.STR_FORMAT, "created_at", createdAt));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "shared_tickets", sharedTickets));
		printStr.append(String.format(Constants.STR_FORMAT, "details", details));
		printStr.append(String.format(Constants.STR_FORMAT, "tag", tag));

		return printStr.toString();

	}

}
