package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.ResourceResponse;
import com.rafsanjani.sandstorm.model.Resource;
import com.rafsanjani.sandstorm.repository.ResourceRepository;
import com.rafsanjani.sandstorm.utility.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource saveResource(Resource resource){

        Resource savedResource = resourceRepository.save(resource);

        if(savedResource != null){

            return savedResource;
        }

        return null;
    }

    @Override
    public List<ResourceResponse> saveResources(List<Resource> resources) {

        List<Resource> resourceList = (List<Resource>) resourceRepository.saveAll(resources);
        List<ResourceResponse> responses = new ArrayList<>();

        if(resourceList.size() > 0){

            for(Resource resource : resourceList){
                responses.add(modelToDto(resource));
            }
        }

        return responses;
    }

    @Override
    public List<ResourceResponse> getResources(){

        List<ResourceResponse> responseList = new ArrayList<>();

        for(Resource resource : resourceRepository.findAll()){

            responseList.add(modelToDto(resource));
        }

        return responseList;
    }

    @Override
    public Resource getResource(String id){

        Optional<Resource> resourceOptional = resourceRepository.findById(id);

        if(resourceOptional.isPresent()){

            return resourceOptional.get();
        }

        return null;
    }

    public ResourceResponse modelToDto(Resource resource){

        if(resource != null){

            return ResourceResponse.builder()
                    .id(resource.getId())
                    .deviceId(resource.getDevice().getId())
                    .deviceName(resource.getDevice().getName())
                    .applicationId(resource.getApplication().getId())
                    .androidVersion(resource.getDevice().getAndroidVersion())
                    .apkVersion(resource.getApplication().getApkVersion())
                    .applicationName(resource.getApplication().getName())
                    .applicationPackage(resource.getApplication().getPackages())
                    .deviceMac(resource.getDevice().getMac())
                    .camera(resource.getCamera())
                    .contact(resource.getContact())
                    .gps(resource.getContact())
                    .memory(resource.getMemory())
                    .microphone(resource.getMicrophone())
                    .sms(resource.getSms())
                    .time(Generator.formatInstant(resource.getRecordedAt()))
                    .build();
        }

        return null;
    }

}
