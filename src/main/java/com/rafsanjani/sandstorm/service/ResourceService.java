package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.ResourceResponse;
import com.rafsanjani.sandstorm.model.Resource;
import java.util.List;

public interface ResourceService {

    Resource saveResource(Resource resource);
    List<ResourceResponse> saveResources(List<Resource> resources);
    List<ResourceResponse> getResources();
    Resource getResource(String id);
}
