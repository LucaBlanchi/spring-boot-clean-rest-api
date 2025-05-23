package it.lucablanchi.itemsspringapi.controller;

import it.lucablanchi.itemsspringapi.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success(HttpStatus.OK.value(), "Ok", null);
    }
}
