package com.spring.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController 
@RequestMapping("/api/v1")
@Tag(name = "Demo REST Controller", description = "Endpoints for testing Swagger documentation")
public class DemoRestController {
    @GetMapping("/welcome")
    @Operation(summary = "get a welcome message", description = "return a greeting message to verify that the REST API n Swagger intergration are functioning correectly")
    @ApiResponse(responseCode = "200", description = "successfully retrieved the entire message")
    public String getWelcomeMessage() {
        return "welcome to spring boot & swagger demo!";
    }
}
