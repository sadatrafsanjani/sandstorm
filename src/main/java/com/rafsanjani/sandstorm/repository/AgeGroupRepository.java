package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.AgeGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface AgeGroupRepository extends CrudRepository<AgeGroup, Long> {
}
