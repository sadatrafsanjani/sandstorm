package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.AgeGroupResponse;
import com.rafsanjani.sandstorm.model.AgeGroup;
import com.rafsanjani.sandstorm.repository.AgeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgeGroupServiceImpl implements AgeGroupService {

    private AgeGroupRepository ageGroupRepository;

    @Autowired
    public AgeGroupServiceImpl(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    @Override
    public Iterable<AgeGroup> getAges(){

        return ageGroupRepository.findAll();
    }

    @Override
    public AgeGroup getAge(long id) {

        Optional<AgeGroup> ageOptional = ageGroupRepository.findById(id);

        if(ageOptional.isPresent()){

            return ageOptional.get();
        }

        return null;
    }

    public List<AgeGroupResponse> getAgeGroups(){

        List<AgeGroupResponse> ageGroupList = new ArrayList<>();

        for(AgeGroup ageGroup : getAges()){

            AgeGroupResponse response = AgeGroupResponse.builder()
                    .id(ageGroup.getId())
                    .name(ageGroup.getName())
                    .range(ageGroup.getRange())
                    .build();
            ageGroupList.add(response);
        }

        return ageGroupList;
    }
}
