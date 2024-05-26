package com.techlinker.user_service.http.response;

import com.techlinker.user_service.dto.request.PostDTORequest;
import com.techlinker.user_service.dto.response.PostDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsByCompanyResponse {
    List<PostDTOResponse> posts;
}
