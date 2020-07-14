package br.com.buspoa.impl.itinerary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItineraryRepository extends MongoRepository<ItineraryEntity, String> {
}
