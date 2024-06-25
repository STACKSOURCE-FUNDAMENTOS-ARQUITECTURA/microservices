package com.techlinker.profile_service.repository;

import com.techlinker.profile_service.entities.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDatabaseRepository extends JpaRepository<Database, Long>{
    List<Database> findByDigitalProfileId(Long id);
    List<Database> findDatabasesByName(String name);
    @Query(value = "SELECT d.* FROM databases d INNER JOIN digital_profiles dp ON d.digital_profile_id = dp.id INNER JOIN developers dev ON dp.developer_id = dev.id WHERE dev.id =:id", nativeQuery = true)
    List<Database> findDatabasesByUserId(Long id);
}
