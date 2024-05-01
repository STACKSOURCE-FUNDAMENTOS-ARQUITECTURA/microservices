package com.techlinker.user_service.feignclients.employmentservice;

import com.techlinker.user_service.dto.request.PostDTORequest;
import com.techlinker.user_service.dto.response.PostDTOResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "employment-service", url = "http://localhost:8084/api/v1/posts")
 public interface EmploymentFeignClient {
 @GetMapping(value = "/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<List<PostDTOResponse>> findPostsByCompanyId (@PathVariable("id") Long id);

 @PostMapping(value = "/companyId={id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<PostDTORequest> createPost(@PathVariable("id") Long id, @Valid @RequestBody PostDTORequest post);

}
