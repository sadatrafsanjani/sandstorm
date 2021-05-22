package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.model.Application;
import com.rafsanjani.sandstorm.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application saveApplication(Application application){

        return applicationRepository.save(application);
    }

    public Application getApplication(String id){

        Optional<Application> applicationOptional = applicationRepository.findById(id);

        if(applicationOptional.isPresent()){

            return applicationOptional.get();
        }

        return null;
    }

    public Iterable<Application> getApplications(){

        return applicationRepository.findAll();
    }
}
