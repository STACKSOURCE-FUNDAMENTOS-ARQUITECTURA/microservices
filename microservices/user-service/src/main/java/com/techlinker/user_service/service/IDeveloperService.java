package com.techlinker.user_service.service;

import com.techlinker.user_service.dto.request.DigitalProfileDTORequest;
import com.techlinker.user_service.entities.Developer;

public interface IDeveloperService extends CrudService<Developer> {
    DigitalProfileDTORequest createDigitalProfile(Long developerId, DigitalProfileDTORequest digitalProfileDTORequest);

}