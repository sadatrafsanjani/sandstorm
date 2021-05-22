package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.model.Application;

public interface ApplicationService {

    Application saveApplication(Application application);
    Application getApplication(String id);
    Iterable<Application> getApplications();
}
