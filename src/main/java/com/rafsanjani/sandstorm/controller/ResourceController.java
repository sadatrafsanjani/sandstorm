package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.dto.request.FeedRequest;
import com.rafsanjani.sandstorm.dto.response.ResourceResponse;
import com.rafsanjani.sandstorm.model.*;
import com.rafsanjani.sandstorm.service.*;
import com.rafsanjani.sandstorm.utility.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private static Logger logger = Logger.getLogger(ResourceController.class.getName());

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

            List<ResourceResponse> responses = resourceService.saveResources(resources);

            return ResponseEntity.ok(responses);
        }

        return ResponseEntity.ok().build();
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

        for(FeedRequest request : list){

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

                    User newUser = new User();
                    newUser.setGender(request.getGender());
                    newUser.setAgeGroup(ageGroupService.getAge(request.getAgeId()));
                    newUser.setEducation(educationService.getEducation(request.getEducationId()));
                    newUser.setArea(areaService.getArea(request.getAreaId()));
                    User user = saveUser(newUser);

                    Device mobileDevice = new Device();
                    mobileDevice.setId(deviceId);
                    mobileDevice.setUser(user);
                    mobileDevice.setName(request.getName());
                    mobileDevice.setMac(request.getMac());
                    mobileDevice.setAndroidVersion(request.getAndroidVersion());
                    device = saveDevice(mobileDevice);
                }

                if(applicationService.getApplication(applicationId) != null){
                    application = applicationService.getApplication(applicationId);
                }
                else{
                    Application app = new Application();
                    app.setId(applicationId);
                    app.setName(request.getApplicationName());
                    app.setPackages(request.getPackageName());
                    app.setApkVersion(request.getApkVersion());
                    application = saveApplication(app);
                }

                Resource resource = new Resource();
                resource.setId(resourceId);
                resource.setDevice(device);
                resource.setApplication(application);
                resource.setCamera(request.getCamera());
                resource.setContact(request.getContact());
                resource.setGps(request.getGps());
                resource.setSms(request.getSms());
                resource.setMicrophone(request.getMicrophone());
                resource.setMemory(request.getMemory());
                resource.setRecordedAt(Instant.now());

                resources.add(resource);
            }
        }

        return resources;
    }
}
