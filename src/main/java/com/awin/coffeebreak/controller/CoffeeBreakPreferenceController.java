package com.awin.coffeebreak.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeBreakPreferenceController {

    //TODO: implement text/html, application/json and text/xml response formats
    @GetMapping(path = "/today")
    public ResponseEntity<?> today(@RequestParam("Accept") String accept) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
