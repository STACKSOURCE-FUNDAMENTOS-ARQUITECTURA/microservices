package com.techlinker.profile_service.service;


import com.techlinker.profile_service.entities.Education;

import java.util.Optional;

public interface IEducationService extends CrudService<Education> {
    Optional<Education> findByDigitalProfileId(Long id) throws Exception;
}
