package com.banking.backend.Banking_Backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {



    @GetMapping("/check")
    public String healtCheck(){
        return "This is Backing App Using Spring Boot";
    }
}
