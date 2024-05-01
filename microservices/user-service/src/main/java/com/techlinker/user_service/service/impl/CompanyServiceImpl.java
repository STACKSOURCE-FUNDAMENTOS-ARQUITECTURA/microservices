package com.techlinker.user_service.service.impl;

import com.techlinker.user_service.dto.request.PostDTORequest;
import com.techlinker.user_service.dto.response.PostDTOResponse;
import com.techlinker.user_service.entities.Company;
import com.techlinker.user_service.feignclients.employmentservice.EmploymentFeignClient;
import com.techlinker.user_service.http.response.PostsByCompanyResponse;
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
    private final EmploymentFeignClient _employmentFeignClient;

    public CompanyServiceImpl(ICompanyRepository companyRepository, EmploymentFeignClient employmentFeignClient) {
        _companyRepository = companyRepository;
        _employmentFeignClient = employmentFeignClient;
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

    @Override
    public PostsByCompanyResponse getPostsByCompanyId(Long companyId) {
        List<PostDTOResponse> posts = _employmentFeignClient.findPostsByCompanyId(companyId).getBody();
        return PostsByCompanyResponse.builder().posts(posts).build();
    }

    @Override
    public PostDTORequest savePostByCompanyId(Long companyId, PostDTORequest newPost) {
        newPost.setCompanyId(companyId);
         PostDTORequest post = _employmentFeignClient.createPost(companyId, newPost).getBody();
        return post;
    }
}