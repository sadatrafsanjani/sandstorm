package com.rafsanjani.sandstorm.service.abstraction;

import com.rafsanjani.sandstorm.dto.response.ResourceResponse;
import com.rafsanjani.sandstorm.model.Resource;
import java.util.List;

public interface ResourceService {

    ResourceResponse saveResource(Resource resource);
    List<ResourceResponse> getResources();
    Resource getResource(String id);
}
