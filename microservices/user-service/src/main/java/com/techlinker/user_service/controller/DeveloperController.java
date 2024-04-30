package com.techlinker.user_service.controller;
import java.util.List;

import com.techlinker.user_service.entities.Developer;
import com.techlinker.user_service.service.IDeveloperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/developers")

public class DeveloperController {
    private final IDeveloperService developerService;

    public DeveloperController(IDeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Developer>> findAllDevelopers() {
        try {
            List<Developer> developers = developerService.getAll();
            if (developers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(developers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Developer> createDeveloper(@Valid @RequestBody Developer developer){
        try {
            Developer developerCreate = developerService.save(developer);
            return new ResponseEntity<>(developerCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}