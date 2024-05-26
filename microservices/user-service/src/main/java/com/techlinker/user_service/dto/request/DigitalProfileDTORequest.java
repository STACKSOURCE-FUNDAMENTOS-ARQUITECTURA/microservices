package com.techlinker.user_service.dto.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalProfileDTORequest {
    private Long id;
    private String name;
    private Long developerId;
}
