package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.DeviceResponse;
import com.rafsanjani.sandstorm.model.Device;
import com.rafsanjani.sandstorm.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device){

        return deviceRepository.save(device);
    }

    @Override
    public Device getDevice(String id){

        Optional<Device> deviceOptional = deviceRepository.findById(id);

        if(deviceOptional.isPresent()){

            return deviceOptional.get();
        }

        return null;
    }

    @Override
    public Iterable<Device> getDevices(){

        return deviceRepository.findAll();
    }

    @Override
    public List<DeviceResponse> devices(){

        List<DeviceResponse> responses = new ArrayList<>();

        for(Device device : getDevices()){

            responses.add(modelToDto(device));
        }

        return responses;
    }

    private DeviceResponse modelToDto(Device device){

        if(device != null){

            return DeviceResponse.builder()
                    .id(device.getId())
                    .userAgeGroup(device.getUser().getAgeGroup().getRange())
                    .userArea(device.getUser().getArea().getName())
                    .userEducation(device.getUser().getEducation().getLevel())
                    .userGender(device.getUser().getGender())
                    .name(device.getName())
                    .mac(device.getMac())
                    .androidVersion(device.getAndroidVersion())
                    .build();
        }

        return null;
    }
}
