package com.zendesk.search.test;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
// @EnableElasticsearchRepositories("org/springframework/data/elasticsearch/repositories")
@EnableElasticsearchRepositories("com.zendesk.search.repository")
public class ElasticSearchTestConfig {

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		final NodeBuilder nodeBuilder = new NodeBuilder();
		return new ElasticsearchTemplate(nodeBuilder.local(true).clusterName("elasticsearch").node().client());
	}

}