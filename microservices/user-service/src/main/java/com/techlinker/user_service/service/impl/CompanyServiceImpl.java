package com.techlinker.user_service.service.impl;

import com.techlinker.user_service.entities.Company;
import com.techlinker.user_service.repository.ICompanyRepository;
import com.techlinker.user_service.service.ICompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository _companyRepository;

    public CompanyServiceImpl(ICompanyRepository companyRepository) {
        _companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public Company save(Company company) throws Exception {
        return _companyRepository.save(company);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        _companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAll() throws Exception {
        return _companyRepository.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) throws Exception {
        return _companyRepository.findById(id);
    }
}