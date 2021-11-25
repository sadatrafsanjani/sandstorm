package com.rafsanjani.sandstorm.dto.request;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedRequest {

  private UserDTO user;
  private DeviceDTO device;
  private List<AppDTO> payload;
}
