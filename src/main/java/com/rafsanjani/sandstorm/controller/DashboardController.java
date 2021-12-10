package com.rafsanjani.sandstorm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping
    public String index(){

        return "index";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }

    @GetMapping("/contact")
    public String contact(){

        return "contact";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy(){

        return "privacy-policy";
    }
}
