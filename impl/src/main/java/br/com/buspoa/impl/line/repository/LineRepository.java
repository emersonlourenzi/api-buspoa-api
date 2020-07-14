package br.com.buspoa.impl.line.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineRepository extends MongoRepository<LineEntity, String> {
}
