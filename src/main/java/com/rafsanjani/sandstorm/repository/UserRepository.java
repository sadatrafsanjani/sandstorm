package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
