package com.techlinker.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTORequest {
    private String title;
    private String description;
    private String imageUrl;
    private Long companyId;
}
