package com.techlinker.profile_service.repository;

import com.techlinker.profile_service.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByEducationId(Long id);
}
