package com.techlinker.user_service.service;

import com.techlinker.user_service.dto.request.PostDTORequest;
import com.techlinker.user_service.entities.Company;
import com.techlinker.user_service.http.response.PostsByCompanyResponse;

public interface ICompanyService extends CrudService<Company> {

    PostsByCompanyResponse getPostsByCompanyId(Long companyId);
    PostDTORequest savePostByCompanyId(Long companyId , PostDTORequest post);

}