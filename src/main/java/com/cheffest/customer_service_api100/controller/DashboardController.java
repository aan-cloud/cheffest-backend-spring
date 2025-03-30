package com.cheffest.customer_service_api100.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/")
    public String dashboard() {
        return "Welcome to the cheffest API! You can see all the API documentation clearly on https://www.postman.com/joint-operations-pilot-8366866/cheffest-spring-api/request/ppmmu06/food?action=share&creator=36210259&ctx=documentation";
    }
}
