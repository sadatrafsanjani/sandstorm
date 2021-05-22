package com.rafsanjani.sandstorm.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class FeedRequest {

  private long ageId;
  private long areaId;
  private long educationId;
  private String name;
  private String gender;
  private String applicationName;
  private String packageName;
  private String apkVersion;
  private String mac;
  private String androidVersion;
  private Boolean camera;
  private Boolean microphone;
  private Boolean gps;
  private Boolean contact;
  private Boolean sms;
  private Boolean memory;
}
