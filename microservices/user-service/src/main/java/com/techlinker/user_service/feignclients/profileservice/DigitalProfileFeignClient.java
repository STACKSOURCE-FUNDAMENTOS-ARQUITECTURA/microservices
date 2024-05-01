package com.techlinker.user_service.feignclients.profileservice;

import com.techlinker.user_service.dto.request.DigitalProfileDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "http://localhost:8085/api/v1/digital_profiles")
public interface DigitalProfileFeignClient {

    @PostMapping(value = "/developerId={developer_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DigitalProfileDTORequest> createDigitalProfile(@PathVariable("developer_id") Long developerId, @RequestBody DigitalProfileDTORequest digitalProfile);
}
