package com.rafsanjani.sandstorm.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgeGroupResponse {

    private Long id;
    private String name;
    private String range;

}
