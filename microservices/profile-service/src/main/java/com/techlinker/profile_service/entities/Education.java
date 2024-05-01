package com.techlinker.profile_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "carreer", nullable = false, length = 50)
    private String career;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "digital_profile_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DigitalProfile digitalProfile;
}
