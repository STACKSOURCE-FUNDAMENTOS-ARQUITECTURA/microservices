package com.techlinker.search_service.repository;

import com.techlinker.search_service.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByNameContaining(String query);
}
