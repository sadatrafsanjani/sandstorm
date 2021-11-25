package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.dto.request.FeedRequest;
import com.rafsanjani.sandstorm.dto.response.ResourceResponse;
import com.rafsanjani.sandstorm.model.*;
import com.rafsanjani.sandstorm.service.abstraction.*;
import com.rafsanjani.sandstorm.utility.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private AreaService areaService;
    private AgeGroupService ageGroupService;
    private EducationService educationService;
    private UserService userService;
    private ApplicationService applicationService;
    private DeviceService deviceService;
    private ResourceService resourceService;

    @Autowired
    public ResourceController(AreaService areaService,
                              AgeGroupService ageGroupService,
                              EducationService educationService,
                              UserService userService,
                              ApplicationService applicationService,
                              DeviceService deviceService,
                              ResourceService resourceService) {
        this.areaService = areaService;
        this.ageGroupService = ageGroupService;
        this.educationService = educationService;
        this.userService = userService;
        this.applicationService = applicationService;
        this.deviceService = deviceService;
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseEntity<?> getResources(){

        List<ResourceResponse> responses = resourceService.getResources();

        if(!responses.isEmpty()){

            return ResponseEntity.ok(responses);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> postFeed(@RequestBody List<FeedRequest> request){

        List<Resource> resources = processResources(request);

        if(!resources.isEmpty()){

            return ResponseEntity.ok(resourceService.saveResources(resources));
        }

        return ResponseEntity.badRequest().build();
    }

    private Application saveApplication(Application application){

        return applicationService.saveApplication(application);
    }

    private Device saveDevice(Device device){

        return deviceService.saveDevice(device);
    }

    private User saveUser(User user){

        return userService.saveUser(user);
    }

    private List<Resource> processResources(List<FeedRequest> list){

        List<Resource> resources = new ArrayList<>();

        list.forEach( request -> {

            String deviceId = Generator.generatePrimaryKey(request.getMac().replaceAll(":", "") + request.getAndroidVersion());
            String applicationId = Generator.generatePrimaryKey(request.getPackageName() + request.getApkVersion());
            String resourceId = Generator.generatePrimaryKey(
                    request.getMac().replaceAll(":", "") +
                            request.getAndroidVersion() +
                            request.getPackageName() +
                            request.getApkVersion()
            );

            if(resourceService.getResource(resourceId) == null){

                Application application;
                Device device;

                if(deviceService.getDevice(deviceId) != null){
                    device = deviceService.getDevice(deviceId);
                }
                else{

                    User user = saveUser(User.builder()
                            .gender(request.getGender())
                            .ageGroup(ageGroupService.getAge(request.getAgeId()))
                            .education(educationService.getEducation(request.getEducationId()))
                            .area(areaService.getArea(request.getAreaId()))
                            .build());

                    device = saveDevice(Device.builder()
                            .id(deviceId)
                            .user(user)
                            .name(request.getName())
                            .mac(request.getMac())
                            .androidVersion(request.getAndroidVersion())
                            .build());
                }

                if(applicationService.getApplication(applicationId) != null){
                    application = applicationService.getApplication(applicationId);
                }
                else{
                    application = saveApplication(Application.builder()
                            .id(applicationId)
                            .name(request.getApplicationName())
                            .packages(request.getPackageName())
                            .apkVersion(request.getApkVersion())
                            .build());
                }

                resources.add(Resource.builder()
                        .id(resourceId)
                        .device(device)
                        .application(application)
                        .camera(request.getCamera())
                        .contact(request.getContact())
                        .gps(request.getGps())
                        .sms(request.getSms())
                        .microphone(request.getMicrophone())
                        .memory(request.getMemory())
                        .recordedAt(Instant.now())
                        .build());
            }
        });

        return resources;
    }
}
