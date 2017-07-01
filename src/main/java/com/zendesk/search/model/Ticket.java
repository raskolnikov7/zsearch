package com.zendesk.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.zendesk.search.util.Constants;

@Document(indexName = "tickets", type = "ticket", shards = 1, replicas = 0, refreshInterval = "-1")

public class Ticket {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isHasIncidents() {
		return hasIncidents;
	}

	public void setHasIncidents(boolean hasIncidents) {
		this.hasIncidents = hasIncidents;
	}

	public String getDueAt() {
		return dueAt;
	}

	public void setDueAt(String dueAt) {
		this.dueAt = dueAt;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	private String url;
	private String externalId;
	private String createdAt;
	private String tag;
	private String type;
	private String subject;
	private String description;
	private String priority;
	private String status;
	private Long submitterId;
	private Long assigneeId;
	private Long organizationId;
	private boolean hasIncidents;
	private String dueAt;
	private String via;

	@Override
	public String toString() {
		return prettyText();
	}

	private String prettyText() {
		StringBuffer printStr = new StringBuffer();
		printStr.append(Constants.LINE);
		printStr.append(String.format(Constants.STR_FORMAT, "_id", id));
		printStr.append(String.format(Constants.STR_FORMAT, "url", url));
		printStr.append(String.format(Constants.STR_FORMAT, "external_id", externalId));
		printStr.append(String.format(Constants.STR_FORMAT, "type", type));
		printStr.append(String.format(Constants.STR_FORMAT, "subject", subject));
		printStr.append(String.format(Constants.STR_FORMAT, "created_at", createdAt));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "has_incidents", hasIncidents));
		printStr.append(String.format(Constants.STR_FORMAT, "description", description));
		printStr.append(String.format(Constants.STR_FORMAT, "priority", priority));
		printStr.append(String.format(Constants.STR_FORMAT, "status", status));
		printStr.append(String.format(Constants.STR_FORMAT, "due_at", dueAt));
		printStr.append(String.format(Constants.STR_FORMAT, "via", via));
		printStr.append(String.format(Constants.STR_FORMAT, "organization_id", organizationId));
		printStr.append(String.format(Constants.STR_FORMAT, "submitter_id", submitterId));
		printStr.append(String.format(Constants.STR_FORMAT, "assignee_id", assigneeId));
		printStr.append(String.format(Constants.STR_FORMAT, "tag", tag));

		return printStr.toString();

	}

}
