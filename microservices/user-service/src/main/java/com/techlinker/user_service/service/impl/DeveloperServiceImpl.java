package com.techlinker.user_service.service.impl;


import java.util.List;
import java.util.Optional;

import com.techlinker.user_service.entities.Developer;
import com.techlinker.user_service.repository.IDeveloperRepository;
import com.techlinker.user_service.service.IDeveloperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeveloperServiceImpl implements IDeveloperService {

    private final IDeveloperRepository developerRepository;

    public DeveloperServiceImpl(IDeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
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

}