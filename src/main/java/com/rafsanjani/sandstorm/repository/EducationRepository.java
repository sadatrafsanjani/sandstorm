package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.Education;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface EducationRepository extends CrudRepository<Education, Long> {
}
