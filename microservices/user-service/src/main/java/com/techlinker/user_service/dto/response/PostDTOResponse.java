package com.techlinker.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTOResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long companyId;
}
