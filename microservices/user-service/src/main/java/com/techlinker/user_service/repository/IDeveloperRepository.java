package com.techlinker.user_service.repository;

import com.techlinker.user_service.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeveloperRepository extends JpaRepository<Developer, Long> {
}