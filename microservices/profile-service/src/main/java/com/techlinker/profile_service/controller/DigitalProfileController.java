package com.techlinker.profile_service.controller;


import com.techlinker.profile_service.entities.DigitalProfile;
import com.techlinker.profile_service.service.IDigitalProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/digital_profiles")
 public class DigitalProfileController {

    private final IDigitalProfileService digitalProfileService;


    public DigitalProfileController(IDigitalProfileService digitalProfileService  ) {
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<DigitalProfile>> findAllDigitalProfiles(){
        try{
            List<DigitalProfile> digitalProfiles = digitalProfileService.getAll();
            if (digitalProfiles.size() > 0){
                return new ResponseEntity<>(digitalProfiles, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<DigitalProfile> findDigitalProfileById(@PathVariable("id") Long id){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(digitalProfile.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/developerId={developer_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<DigitalProfile> createDigitalProfile(@PathVariable("developer_id") Long developerId, @RequestBody DigitalProfile digitalProfile){
        try {
                digitalProfile.setDeveloperId(developerId);
                DigitalProfile newDigitalProfile = digitalProfileService.save(digitalProfile);
                return ResponseEntity.status(HttpStatus.CREATED).body(newDigitalProfile);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<DigitalProfile> updateDigitalProfile(@PathVariable("id") Long id, @RequestBody DigitalProfile digitalProfile){
        try{
            Optional<DigitalProfile> digitalProfile1 = digitalProfileService.getById(id);
            if (!digitalProfile1.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                digitalProfile.setId(id);
                digitalProfileService.save(digitalProfile);
                return new ResponseEntity<>(digitalProfile, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<DigitalProfile> deleteDigitalProfile(@PathVariable("id") Long id){
        try{
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                digitalProfileService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<DigitalProfile> findDigitalProfileByDeveloperId(@PathVariable("id") Long id){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.findDigitalProfileByDeveloperId(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(digitalProfile.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
