package com.rafsanjani.sandstorm.service.implementation;

import com.rafsanjani.sandstorm.model.Education;
import com.rafsanjani.sandstorm.repository.EducationRepository;
import com.rafsanjani.sandstorm.service.abstraction.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public Education getEducation(long id){

        Optional<Education> educationOptional = educationRepository.findById(id);


        if(educationOptional.isPresent()){

            return educationOptional.get();
        }

        return null;
    }
}
