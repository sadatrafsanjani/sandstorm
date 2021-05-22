package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.AgeGroupResponse;
import com.rafsanjani.sandstorm.model.AgeGroup;

import java.util.List;

public interface AgeGroupService {

    Iterable<AgeGroup> getAges();
    AgeGroup getAge(long id);
    List<AgeGroupResponse> getAgeGroups();
}
