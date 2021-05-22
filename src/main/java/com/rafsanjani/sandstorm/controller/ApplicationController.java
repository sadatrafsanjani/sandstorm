package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.model.Application;
import com.rafsanjani.sandstorm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public Iterable<Application> getApplications(){

        return applicationService.getApplications();
    }

    @GetMapping("/{id}")
    public Application getApplication(@PathVariable("id") String id){

        return applicationService.getApplication(id);
    }
}
