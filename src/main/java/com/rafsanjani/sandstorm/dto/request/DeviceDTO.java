package com.rafsanjani.sandstorm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDTO {

    private String name;
    private String mac;
    private String androidVersion;
}
