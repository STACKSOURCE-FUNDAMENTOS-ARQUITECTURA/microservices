package com.techlinker.profile_service.service;


import com.techlinker.profile_service.entities.StudyCenter;

import java.util.List;

public interface IStudyCenterService extends CrudService<StudyCenter> {
    List<StudyCenter> findByEducationId(Long id) throws Exception;
}

