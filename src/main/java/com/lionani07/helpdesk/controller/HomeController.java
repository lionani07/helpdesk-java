package com.lionani07.helpdesk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return String.format("Help desk is up! version: %s", LocalDate.now());
    }
}
