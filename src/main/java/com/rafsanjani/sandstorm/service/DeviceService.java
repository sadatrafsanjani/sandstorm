package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.dto.response.DeviceResponse;
import com.rafsanjani.sandstorm.model.Device;

import java.util.List;

public interface DeviceService {

    Device saveDevice(Device device);
    Device getDevice(String id);
    Iterable<Device> getDevices();
    List<DeviceResponse> devices();
}
