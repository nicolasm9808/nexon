package com.nexon.nexon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsController {

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build(); // Responde con un OK a la solicitud OPTIONS
    }
}
