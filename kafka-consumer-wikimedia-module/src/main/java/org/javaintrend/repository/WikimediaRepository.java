package org.javaintrend.repository;

import org.javaintrend.entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaRepository extends MongoRepository<Wikimedia,Integer> {
}
