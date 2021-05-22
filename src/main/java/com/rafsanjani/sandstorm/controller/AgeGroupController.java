package com.rafsanjani.sandstorm.controller;

import com.rafsanjani.sandstorm.dto.response.AgeGroupResponse;
import com.rafsanjani.sandstorm.service.AgeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/ages")
public class AgeGroupController {

    private AgeGroupService ageGroupService;

    @Autowired
    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @GetMapping
    public ResponseEntity<?> ages(){

        List<AgeGroupResponse> responses = ageGroupService.getAgeGroups();

        if(!responses.isEmpty()){

            return ResponseEntity.ok(responses);
        }

        return ResponseEntity.ok().build();
    }
}
