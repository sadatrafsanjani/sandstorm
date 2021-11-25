package com.rafsanjani.sandstorm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private long ageId;
    private long areaId;
    private long educationId;
    private String gender;
}
