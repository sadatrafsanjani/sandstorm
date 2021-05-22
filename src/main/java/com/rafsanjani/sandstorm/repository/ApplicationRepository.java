package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApplicationRepository extends CrudRepository<Application, String> {
}
