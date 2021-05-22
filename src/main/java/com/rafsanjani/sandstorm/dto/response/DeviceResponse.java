package com.rafsanjani.sandstorm.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeviceResponse {

    private String id;
    private String userAgeGroup;
    private String userArea;
    private String userEducation;
    private String userGender;
    private String name;
    private String mac;
    private String androidVersion;
}
