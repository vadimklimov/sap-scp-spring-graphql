package com.doc.scp.demo.graphql.resolver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.doc.scp.demo.graphql.type.Album;

@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(QueryResolver.class);

	public List<Album> album(String title) {
		LOG.info("Calling query resolver for query 'album'");

		Query query = new Query();
		query.addCriteria(Criteria	.where("title")
									.is(title));
		return mongoTemplate.find(query, Album.class);
	}

}
