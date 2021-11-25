package com.rafsanjani.sandstorm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppDTO {

  private String applicationName;
  private String packageName;
  private String apkVersion;
  private Boolean camera;
  private Boolean microphone;
  private Boolean gps;
  private Boolean contact;
  private Boolean sms;
  private Boolean memory;
}
