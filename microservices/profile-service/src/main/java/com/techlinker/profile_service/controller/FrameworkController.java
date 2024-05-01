package com.techlinker.profile_service.controller;


import com.techlinker.profile_service.entities.DigitalProfile;
import com.techlinker.profile_service.entities.Framework;
import com.techlinker.profile_service.service.IDigitalProfileService;
import com.techlinker.profile_service.service.IFrameworkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/frameworks")
 public class FrameworkController {
    private final IFrameworkService frameworkService;
    private final IDigitalProfileService digitalProfileService;

    public FrameworkController(IFrameworkService frameworkService, IDigitalProfileService digitalProfileService) {
        this.frameworkService = frameworkService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Framework>> findAllFrameworks(){
        try {
            List<Framework> frameworks = frameworkService.getAll();
            if(frameworks.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(frameworks, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Framework> findFrameworkById(@PathVariable("id") Long id){
        try {
            Optional<Framework> framework = frameworkService.getById(id);
            return framework.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/digitalProfileId={id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Framework> insertFramework(@PathVariable("id")Long digitalProfileId , @Valid @RequestBody Framework framework) {
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                framework.setDigitalProfile(digitalProfile.get());
                Framework newFramework = frameworkService.save(framework);
                return ResponseEntity.status(HttpStatus.CREATED).body(newFramework);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Framework> updateFramework(@PathVariable("id") Long id, @Valid @RequestBody Framework framework) {
        try {
            Optional<Framework> frameworkUpdate = frameworkService.getById(id);
            if(!frameworkUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            framework.setId(id);
            frameworkService.save(framework);
            return new ResponseEntity<>(framework, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Framework> deleteFramework(@PathVariable("id") Long id) {
        try {
            Optional<Framework> frameworkDelete = frameworkService.getById(id);
            if(!frameworkDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            frameworkService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Framework>> findFrameworkByDigitalProfileId(@PathVariable("id") Long digitalProfileId){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Framework> frameworks = frameworkService.findByDigitalProfileId(digitalProfileId);
                return new ResponseEntity<>(frameworks, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Framework>> findFrameworkByDeveloperId(@PathVariable("id") Long developerId){
        try {
                List<Framework> frameworks = frameworkService.findByDeveloperId(developerId);
                return new ResponseEntity<>(frameworks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
