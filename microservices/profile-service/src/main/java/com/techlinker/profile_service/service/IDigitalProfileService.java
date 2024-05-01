package com.techlinker.profile_service.service;


import com.techlinker.profile_service.entities.DigitalProfile;

import java.util.Optional;

public interface IDigitalProfileService extends CrudService<DigitalProfile> {

    Optional<DigitalProfile> findDigitalProfileByDeveloperId(Long id) throws Exception;
}
