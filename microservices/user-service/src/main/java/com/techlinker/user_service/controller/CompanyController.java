package com.techlinker.user_service.controller;

import com.techlinker.user_service.entities.Company;
import com.techlinker.user_service.service.ICompanyService;
import com.techlinker.user_service.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/companies")

public class CompanyController {

    private final ICompanyService companyService;
    private final IUserService userService;

    public CompanyController(ICompanyService companyService, IUserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Company>> findAllCompanies() {
        try {
            List<Company> companies = companyService.getAll();
            if (companies.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(companies, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company){
        try {
            Company companyCreate = companyService.save(company);
            return new ResponseEntity<>(companyCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}