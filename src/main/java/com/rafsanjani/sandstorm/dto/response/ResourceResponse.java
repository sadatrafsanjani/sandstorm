package com.rafsanjani.sandstorm.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResourceResponse {

    private String id;
    private String deviceId;
    private String deviceName;
    private String applicationId;
    private String token;
    private String androidVersion;
    private String applicationName;
    private String applicationPackage;
    private String apkVersion;
    private Boolean camera;
    private Boolean microphone;
    private Boolean gps;
    private Boolean contact;
    private Boolean sms;
    private Boolean memory;
    private String time;
}
