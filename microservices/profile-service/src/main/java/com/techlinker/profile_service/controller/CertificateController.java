package com.techlinker.profile_service.controller;

import com.techlinker.profile_service.entities.Certificate;
import com.techlinker.profile_service.entities.Education;
import com.techlinker.profile_service.service.ICertificateService;
import com.techlinker.profile_service.service.IEducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/certificates")
@CrossOrigin(origins = "*")
public class CertificateController {
    private final ICertificateService certificateService;
    private final IEducationService educationService;

    public CertificateController(ICertificateService certificateService, IEducationService educationService) {
        this.certificateService = certificateService;
        this.educationService = educationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> findAllCertificates(){
        try {
            List<Certificate> certificates = certificateService.getAll();
            if (certificates.size() > 0){
                return new ResponseEntity<>(certificates, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certificate> findCertificateById(@PathVariable("id") Long id){
        try {
            Optional<Certificate> certificate = certificateService.getById(id);
            if (!certificate.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(certificate.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/education/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certificate> insertCertificate(@PathVariable("id")Long educationId, @RequestBody Certificate certificate){
        try {
            Optional<Education> education  = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                certificate.setEducation(education.get());
                Certificate newCertificate = certificateService.save(certificate);
                return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //find certificates by education id
    @GetMapping(value = "/education/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> findCertificateByEducationId(@PathVariable("id") Long id){
        try {
            Optional<Education> education = educationService.getById(id);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Certificate> certificates = certificateService.findByEducationId(id);
                return new ResponseEntity<>(certificates, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
