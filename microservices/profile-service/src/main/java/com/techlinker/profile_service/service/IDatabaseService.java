package com.techlinker.profile_service.service;

import com.techlinker.profile_service.entities.Database;
import java.util.List;
import java.util.Optional;

public interface IDatabaseService extends CrudService<Database>{
    List<Database> findByDigitalProfileId(Long id) throws Exception;
    List<Database> findByName(String name) throws Exception;
    List<Database> findByDeveloperId(Long id) throws Exception;
}
