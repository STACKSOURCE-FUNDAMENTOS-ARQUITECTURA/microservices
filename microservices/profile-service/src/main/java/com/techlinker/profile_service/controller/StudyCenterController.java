package com.techlinker.profile_service.controller;

import com.techlinker.profile_service.entities.Education;
import com.techlinker.profile_service.entities.StudyCenter;
import com.techlinker.profile_service.service.IEducationService;
import com.techlinker.profile_service.service.IStudyCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/study-centers")
 public class StudyCenterController {

    private final IStudyCenterService studyCenterService;
    private final IEducationService educationService;
    public StudyCenterController(IStudyCenterService studyCenterService, IEducationService educationService) {
        this.studyCenterService = studyCenterService;
        this.educationService = educationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<StudyCenter>> findAllStudyCenters(){
        try {
            List<StudyCenter> studyCenters = studyCenterService.getAll();
            if (studyCenters.size() > 0){
                return new ResponseEntity<>(studyCenters, HttpStatus.OK);
            }
            else {
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<StudyCenter> findStudyCenterById(@PathVariable("id") Long id){
        try {
            Optional<StudyCenter> studyCenter = studyCenterService.getById(id);
            if (!studyCenter.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(studyCenter.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/educationId={id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<StudyCenter> insertStudyCenter(@PathVariable("id") Long educationId ,  @RequestBody StudyCenter studyCenter){
        try {
            Optional<Education> education = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                studyCenter.setEducation(education.get());
                StudyCenter newStudyCenter = studyCenterService.save(studyCenter);
                return ResponseEntity.status(HttpStatus.CREATED).body(newStudyCenter);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/education/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<StudyCenter>> findStudyCentersByEducationId(@PathVariable("id") Long educationId){
        try {
            Optional<Education> education = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<StudyCenter> studyCenters = studyCenterService.findByEducationId(educationId);
                return new ResponseEntity<>(studyCenters, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
