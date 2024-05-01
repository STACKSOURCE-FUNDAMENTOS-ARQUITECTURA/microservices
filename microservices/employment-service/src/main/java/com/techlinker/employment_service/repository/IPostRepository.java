package com.techlinker.employment_service.repository;


import com.techlinker.employment_service.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCompanyId(Long companyId);
}
