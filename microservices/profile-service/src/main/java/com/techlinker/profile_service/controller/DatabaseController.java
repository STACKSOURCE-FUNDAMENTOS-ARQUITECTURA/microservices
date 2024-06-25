package com.techlinker.profile_service.controller;

import com.techlinker.profile_service.entities.Database;
import com.techlinker.profile_service.entities.DigitalProfile;
import com.techlinker.profile_service.service.IDatabaseService;
import com.techlinker.profile_service.service.IDigitalProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/databases")
public class DatabaseController {
    private final IDatabaseService databaseService;
    private final IDigitalProfileService digitalProfileService;

    public DatabaseController(IDatabaseService databaseService, IDigitalProfileService digitalProfileService) {
        this.databaseService = databaseService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Database>> findAllDatabases(){
        try {
            List<Database> databases = databaseService.getAll();
            if(databases.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(databases, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Database> findDatabaseById(@PathVariable("id") Long id){
        try {
            Optional<Database> database = databaseService.getById(id);
            return database.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping( value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Database> insertDatabase(@PathVariable("id")Long digitalProfileId, @Valid @RequestBody Database database){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                database.setDigitalProfile(digitalProfile.get());
                Database newDatabase = databaseService.save(database);
                return ResponseEntity.status(HttpStatus.CREATED).body(newDatabase);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Database> updateDatabase(@PathVariable("id") Long id, @Valid @RequestBody Database database){
        try {
            Optional<Database> currentDatabase = databaseService.getById(id);
            if(!currentDatabase.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            database.setId(id);
            databaseService.save(database);
            return new ResponseEntity<>(database, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Database> deleteDatabase(@PathVariable("id") Long id){
        try {
            Optional<Database> currentDatabase = databaseService.getById(id);
            if(!currentDatabase.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            databaseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Database>> findDatabasesByDigitalProfileId(@PathVariable("id") Long digitalProfileId){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Database> databases = databaseService.findByDigitalProfileId(digitalProfileId);
                return new ResponseEntity<>(databases, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Database>> findDatabaseByDeveloperId(@PathVariable("id") Long developerId) {
        try {
            List<Database> programmingLanguages = databaseService.findByDeveloperId(developerId);
            return new ResponseEntity<>(programmingLanguages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
