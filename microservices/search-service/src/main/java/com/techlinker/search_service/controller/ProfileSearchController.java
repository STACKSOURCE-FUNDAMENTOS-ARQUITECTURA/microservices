package com.techlinker.search_service.controller;

import com.techlinker.search_service.service.ProfileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileSearchController {

    @Autowired
    private ProfileSearchService profileSearchService;

    @GetMapping("/search")
    public ResponseEntity<?> searchProfiles(@RequestParam String query) {
        return ResponseEntity.ok(profileSearchService.searchProfiles(query));
    }
}
