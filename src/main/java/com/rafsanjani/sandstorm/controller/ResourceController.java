package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.dto.request.AppDTO;
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
    public ResponseEntity<?> postFeed(@RequestBody FeedRequest request){

        List<ResourceResponse> resources = processResources(request);

        if(!resources.isEmpty()){

            return ResponseEntity.ok(resources);
        }

        return ResponseEntity.ok().build();
    }

    private List<ResourceResponse> processResources(FeedRequest feed){

        List<ResourceResponse> resources = new ArrayList<>();

        String deviceId = Generator.generatePrimaryKey(feed.getDevice().getMac().replaceAll(":", "") + feed.getDevice().getAndroidVersion());
        Device device;

        //Check Device if exist in database
        if(deviceService.getDevice(deviceId) == null){

            User user = userService.saveUser(User.builder()
                    .gender(feed.getUser().getGender())
                    .ageGroup(ageGroupService.getAge(feed.getUser().getAgeId()))
                    .education(educationService.getEducation(feed.getUser().getEducationId()))
                    .area(areaService.getArea(feed.getUser().getAreaId()))
                    .build());

            device = deviceService.saveDevice(Device.builder()
                    .id(deviceId)
                    .user(user)
                    .name(feed.getDevice().getName())
                    .mac(feed.getDevice().getMac())
                    .androidVersion(feed.getDevice().getAndroidVersion())
                    .build());
        }
        else{

            device = deviceService.getDevice(deviceId);
        }

        feed.getPayload().forEach( app -> {

            String applicationId = Generator.generatePrimaryKey(app.getPackageName() + app.getApkVersion());
            Application application;

            //Check if application exist in database
            if(applicationService.getApplication(applicationId) == null){
                application = applicationService.saveApplication(Application.builder()
                        .id(applicationId)
                        .name(app.getApplicationName())
                        .packages(app.getPackageName())
                        .apkVersion(app.getApkVersion())
                        .build());
            }
            else{
                application = applicationService.getApplication(applicationId);
            }

            String status = generateStatus(app);
            String resourceId = Generator.generatePrimaryKey(deviceId + applicationId + status);
            Resource resource = resourceService.getResource(resourceId);

            //Check app record of specific device not exist in database
            if(resource == null){
                ResourceResponse response = resourceService.saveResource(Resource.builder()
                                .id(resourceId)
                                .device(device)
                                .application(application)
                                .camera(app.getCamera())
                                .microphone(app.getMicrophone())
                                .memory(app.getMemory())
                                .sms(app.getSms())
                                .contact(app.getContact())
                                .gps(app.getGps())
                                .status(generateStatus(app))
                                .recordedAt(Instant.now())
                        .build());
                resources.add(response);
            }
        });

        return resources;
    }

    private String generateStatus(AppDTO dto){

        return checkState(dto.getCamera())
                + checkState(dto.getMicrophone())
                + checkState(dto.getMemory())
                + checkState(dto.getSms())
                + checkState(dto.getContact())
                + checkState(dto.getGps());
    }

    private String checkState(boolean state){

        return state ? "1" : "0";
    }
}
