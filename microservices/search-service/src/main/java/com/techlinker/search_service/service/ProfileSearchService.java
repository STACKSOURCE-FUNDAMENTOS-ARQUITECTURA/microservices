package com.techlinker.search_service.service;

import com.techlinker.search_service.model.Profile;
import com.techlinker.search_service.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileSearchService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> searchProfiles(String query) {
        return profileRepository.findByNameContaining(query);
    }
}
