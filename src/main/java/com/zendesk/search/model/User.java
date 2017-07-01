package com.zendesk.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.zendesk.search.util.Constants;

@Document(indexName = "users", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")

public class User {

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(String lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
	private Long id;
	private String url;
	private String externalId;
	private String name;
	private String alias;
	private String createdAt;
	private boolean active;
	private boolean verified;
	private boolean shared;
	private String locale;
	private String timezone;
	private String lastLoginAt;
	private String email;
	private String phone;
	private String signature;
	private Long organizationId;
	private boolean suspended;
	private String role;
	@Field(type = FieldType.String)
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
		printStr.append(String.format(Constants.STR_FORMAT, "alias", alias));
		printStr.append(String.format(Constants.STR_FORMAT, "created_at", createdAt));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "active", active));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "verified", verified));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "shared", shared));
		printStr.append(String.format(Constants.STR_FORMAT, "locale", locale));
		printStr.append(String.format(Constants.STR_FORMAT, "timezone", timezone));
		printStr.append(String.format(Constants.STR_FORMAT, "last_login_at", lastLoginAt));
		printStr.append(String.format(Constants.STR_FORMAT, "email", email));
		printStr.append(String.format(Constants.STR_FORMAT, "phone", phone));
		printStr.append(String.format(Constants.STR_FORMAT, "signature", signature));
		printStr.append(String.format(Constants.LONG_FORMAT, "organization_id", organizationId));
		printStr.append(String.format(Constants.BOOLEAN_FORMAT, "suspended", suspended));
		printStr.append(String.format(Constants.STR_FORMAT, "tag", tag));
		printStr.append(String.format(Constants.STR_FORMAT, "role", role));

		return printStr.toString();

	}

}
