package com.techlinker.profile_service.service;

import com.techlinker.profile_service.entities.Certificate;

import java.util.List;

public interface ICertificateService extends CrudService<Certificate> {
    List<Certificate> findByEducationId(Long id) throws Exception;
}
