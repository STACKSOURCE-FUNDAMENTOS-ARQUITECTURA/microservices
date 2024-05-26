package com.techlinker.user_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "developers")
@Data
@NoArgsConstructor

public class Developer extends User {
    public Developer(Long id, String firstName, String lastName, String email, String phone, String password, String role, String description, String image, String bannerImage) {
        super(id, firstName, lastName, email, phone, password, role, description, image, bannerImage);
    }
}