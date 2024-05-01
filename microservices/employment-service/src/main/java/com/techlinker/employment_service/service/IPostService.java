package com.techlinker.employment_service.service;



import com.techlinker.employment_service.entities.Post;

import java.util.List;

public interface IPostService extends CrudService<Post> {
    List<Post> findByCompanyId(Long companyId) throws Exception;
}
