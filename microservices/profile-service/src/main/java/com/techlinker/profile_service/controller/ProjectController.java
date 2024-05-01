package com.techlinker.profile_service.controller;


import com.techlinker.profile_service.entities.DigitalProfile;
import com.techlinker.profile_service.entities.Project;
import com.techlinker.profile_service.service.IDigitalProfileService;
import com.techlinker.profile_service.service.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/projects")
 public class ProjectController {

    private final IProjectService projectService;
    private final IDigitalProfileService digitalProfileService;

    public ProjectController(IProjectService projectService, IDigitalProfileService digitalProfileService) {
        this.projectService = projectService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Project>> findAllProjects(){
        try {
            List<Project> projects = projectService.getAll();
            if (projects.size() > 0){
                return new ResponseEntity<>(projects, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Project> findProjectById(@PathVariable("id") Long id){
        try {
            Optional<Project> project = projectService.getById(id);
            if (!project.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(project.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/digitalProfileId={id}" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Project> insertProject(@PathVariable("id") Long digitalProfileId , @RequestBody Project project){
        try {
             Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
                if (!digitalProfile.isPresent()){
                    return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
                }
                else {
                    project.setDigitalProfile(digitalProfile.get());
                    Project newProject = projectService.save(project);
                    return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
                }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Project>> findProjectsByDigitalProfileId(@PathVariable("id") Long digitalProfileId){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Project> projects = projectService.findByDigitalProfileId(digitalProfileId);
                return new ResponseEntity<>(projects, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete project by id
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Project> deleteProjectById(@PathVariable("id") Long id){
        try {
            Optional<Project> project = projectService.getById(id);
            if (!project.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                projectService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}