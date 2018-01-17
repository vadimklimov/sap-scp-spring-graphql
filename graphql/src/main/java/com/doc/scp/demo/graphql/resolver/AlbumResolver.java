package com.doc.scp.demo.graphql.resolver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.doc.scp.demo.graphql.type.Album;
import com.doc.scp.demo.graphql.type.Artist;
import com.doc.scp.demo.graphql.type.Track;

@Component
public class AlbumResolver implements GraphQLResolver<Album> {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(AlbumResolver.class);

	public Artist getArtist(Album album) {
		LOG.info("Calling field resolver of object type 'Album' for object type 'Artist'. Album ID: {}, artist ID: {}",
				album.getId(), album.getArtistId());

		return mongoTemplate.findById(album.getArtistId(), Artist.class);
	}

	public List<Track> getTracks(Album album) {
		LOG.info("Calling field resolver of object type 'Album' for object type 'Track'. Album ID: {}", album.getId());

		Query query = new Query();
		query.addCriteria(Criteria	.where("albumId")
									.is(album.getId()));
		return mongoTemplate.find(query, Track.class);
	}

}
