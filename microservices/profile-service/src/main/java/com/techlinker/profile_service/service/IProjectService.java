package com.techlinker.profile_service.service;


import com.techlinker.profile_service.entities.Project;

import java.util.List;

public interface IProjectService extends CrudService<Project> {
    List<Project> findByDigitalProfileId(Long id) throws Exception;
}
