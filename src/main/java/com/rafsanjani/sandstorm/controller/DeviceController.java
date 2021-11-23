package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.dto.response.DeviceResponse;
import com.rafsanjani.sandstorm.model.Device;
import com.rafsanjani.sandstorm.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<?> getDevices(){

        List<DeviceResponse> responses = deviceService.devices();

        if (!responses.isEmpty()) {

            return ResponseEntity.ok(responses);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable("id") String id){

        return deviceService.getDevice(id);
    }
}
