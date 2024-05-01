package com.techlinker.employment_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;
    @Column(name = "company_id", nullable = false)
    private Long companyId;
}
