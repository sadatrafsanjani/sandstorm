package com.rafsanjani.sandstorm.repository;

import com.rafsanjani.sandstorm.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeviceRepository extends CrudRepository<Device, String> {
}
