package com.techlinker.profile_service.service;

import com.techlinker.profile_service.entities.ProgrammingLanguage;

import java.util.List;

public interface IProgrammingLanguageService extends CrudService<ProgrammingLanguage>{
    List<ProgrammingLanguage> findByDigitalProfileId(Long id) throws Exception;
    List<ProgrammingLanguage> findByName(String name) throws Exception;
    List<ProgrammingLanguage> findByDeveloperId(Long id) throws Exception;
}
