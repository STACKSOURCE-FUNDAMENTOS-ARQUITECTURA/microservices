package com.techlinker.profile_service.repository;
import com.techlinker.profile_service.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Long> {
    Optional<Education> findByDigitalProfileId(Long id);
}
