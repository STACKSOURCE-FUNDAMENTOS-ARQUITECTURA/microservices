//package com.techlinker.profile_service.controller;
//
//
//import com.techlinker.profile_service.entities.DigitalProfile;
//import com.techlinker.profile_service.entities.Education;
//import com.techlinker.profile_service.service.IDigitalProfileService;
//import com.techlinker.profile_service.service.IEducationService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/v1/educations")
// public class EducationController {
//
//    private final IEducationService educationService;
//    private final IDigitalProfileService digitalProfileService;
//
//    public EducationController(IEducationService educationService, IDigitalProfileService digitalProfileService) {
//        this.educationService = educationService;
//        this.digitalProfileService = digitalProfileService;
//    }
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public ResponseEntity<List<Education>> findAllEducations(){
//        try {
//            List<Education> educations = educationService.getAll();
//            if (educations.size() > 0){
//                return new ResponseEntity<>(educations, HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public ResponseEntity<Education> findEducationById(@PathVariable("id") Long id){
//        try {
//            Optional<Education> education = educationService.getById(id);
//            if (!education.isPresent()){
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            else {
//                return new ResponseEntity<>(education.get(), HttpStatus.OK);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping(value = "/digitalProfileId={id}" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Education> insertEducation(@PathVariable("id")Long idDigitalProfile, @RequestBody Education education){
//        try{
//            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(idDigitalProfile);
//            if(!digitalProfile.isPresent())
//                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
//
//            //see if this digitalProfile already has an education
//            Optional<Education> educationOptional = educationService.findByDigitalProfileId(idDigitalProfile);
//            if(educationOptional.isPresent())
//                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
//
//            education.setDigitalProfile(digitalProfile.get());
//            Education newEducation = educationService.save(education);
//            return ResponseEntity.status(HttpStatus.CREATED).body(newEducation);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public ResponseEntity<Education> findEducationByDigitalProfile(@PathVariable("id") Long id){
//        try {
//            Optional<Education> education = educationService.findByDigitalProfileId(id);
//            if (!education.isPresent()){
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            else {
//                return new ResponseEntity<>(education.get(), HttpStatus.OK);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
