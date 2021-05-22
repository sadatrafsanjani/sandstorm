package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AreaRepository extends CrudRepository<Area, Long> {
}
