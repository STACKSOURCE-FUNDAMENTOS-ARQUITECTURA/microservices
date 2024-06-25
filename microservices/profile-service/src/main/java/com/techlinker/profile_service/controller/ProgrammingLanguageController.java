package com.techlinker.profile_service.controller;

import com.techlinker.profile_service.entities.DigitalProfile;
import com.techlinker.profile_service.entities.ProgrammingLanguage;
import com.techlinker.profile_service.service.IDigitalProfileService;
import com.techlinker.profile_service.service.IProgrammingLanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/programmingLanguages")
public class ProgrammingLanguageController {
    private final IProgrammingLanguageService programmingLanguageService;
    private final IDigitalProfileService digitalProfileService;

    public ProgrammingLanguageController(IProgrammingLanguageService programmingLanguageService, IDigitalProfileService digitalProfileService) {
        this.programmingLanguageService = programmingLanguageService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgrammingLanguage>> findAllProgrammingLanguages() {
        try {
            List<ProgrammingLanguage> programmingLanguages = programmingLanguageService.getAll();
            if (programmingLanguages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(programmingLanguages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProgrammingLanguage> findProgrammingLanguageById(@PathVariable("id") Long id) {
        try {
            return programmingLanguageService.getById(id)
                    .map(programmingLanguage -> new ResponseEntity<>(programmingLanguage, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProgrammingLanguage> createProgrammingLanguage(@Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        try {
            ProgrammingLanguage savedProgrammingLanguage = programmingLanguageService.save(programmingLanguage);
            return new ResponseEntity<>(savedProgrammingLanguage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProgrammingLanguage> updateProgrammingLanguage(@PathVariable("id") Long id, @Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        try {
            Optional<ProgrammingLanguage> existingProgrammingLanguage = programmingLanguageService.getById(id);
            if (!existingProgrammingLanguage.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            programmingLanguage.setId(id);
            programmingLanguageService.save(programmingLanguage);
            return new ResponseEntity<>(programmingLanguage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProgrammingLanguage(@PathVariable("id") Long id) {
        try {
            if (!programmingLanguageService.getById(id).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            programmingLanguageService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgrammingLanguage>> findProgrammingLanguagesByDigitalProfileId(@PathVariable("id") Long digitalProfileId) {
        try {
            if (!digitalProfileService.getById(digitalProfileId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<ProgrammingLanguage> programmingLanguages = programmingLanguageService.findByDigitalProfileId(digitalProfileId);
            return new ResponseEntity<>(programmingLanguages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgrammingLanguage>> findProgrammingLanguagesByDeveloperId(@PathVariable("id") Long developerId) {
        try {
            List<ProgrammingLanguage> programmingLanguages = programmingLanguageService.findByDeveloperId(developerId);
            return new ResponseEntity<>(programmingLanguages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
