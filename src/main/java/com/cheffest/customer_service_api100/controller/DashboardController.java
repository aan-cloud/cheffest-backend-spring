package com.cheffest.customer_service_api100.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/")
    public String dashboard() {
        return "Welcome to the cheffest API! You can see all the API documentation clearly on https://blabla.com";
    }
}
