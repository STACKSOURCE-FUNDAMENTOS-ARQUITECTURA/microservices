package com.techlinker.messaging_service.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T> {

    T save (T t) throws Exception;
    void delete (Long id) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(Long id) throws Exception;
}