package com.techlinker.user_service.service.impl;


import java.util.List;
import java.util.Optional;

import com.techlinker.user_service.dto.request.DigitalProfileDTORequest;
import com.techlinker.user_service.entities.Developer;
import com.techlinker.user_service.feignclients.profileservice.DigitalProfileFeignClient;
import com.techlinker.user_service.repository.IDeveloperRepository;
import com.techlinker.user_service.service.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeveloperServiceImpl implements IDeveloperService {

    private final IDeveloperRepository developerRepository;

    private final DigitalProfileFeignClient digitalProfileFeignClient;

    public DeveloperServiceImpl(IDeveloperRepository developerRepository, DigitalProfileFeignClient digitalProfileFeignClient) {
        this.developerRepository = developerRepository;
        this.digitalProfileFeignClient = digitalProfileFeignClient;
    }

    @Override
    @Transactional
    public Developer save(Developer developer) throws Exception {

        return developerRepository.save(developer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        developerRepository.deleteById(id);
    }

    @Override
    public List<Developer> getAll() throws Exception {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> getById(Long id) throws Exception {
        return developerRepository.findById(id);
    }

    @Override
    public DigitalProfileDTORequest createDigitalProfile(Long developerId, DigitalProfileDTORequest newDigitalProfile) {
        newDigitalProfile.setDeveloperId(developerId);
        DigitalProfileDTORequest digitalProfile = digitalProfileFeignClient.createDigitalProfile(developerId, newDigitalProfile).getBody();
        return digitalProfile;
    }
}