package com.ecommerce.module.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.ApiResponse;
import com.ecommerce.module.user.service.UserOperations;
import com.ecommerce.module.user.value.UserRequest;
import com.ecommerce.module.user.value.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/users")
@Tag(name = "User Operations", description = "Endpoints for handling user-related functionalities")
@RequiredArgsConstructor
public class UserController {
    
    private final UserOperations userOperations;


    @PutMapping("/{userId}")
	@Operation(summary = "update user details")
    public ResponseEntity<UserResponse> putMethodName( @PathVariable Long userId, @Valid @RequestBody UserRequest request) {
        //TODO: process PUT request
        UserResponse updateUserDetails = userOperations.updateUserDetails(userId, request);;
        return ResponseEntity.ok(updateUserDetails);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "fetch all user details")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userOperations.getUser(userId));
    }

    @GetMapping("/")
    @Operation(summary = "fetch all user details")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userOperations.getAllUser());
    }
        
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	@Operation(summary = "delete user")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
		userOperations.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivateUser(@PathVariable Long id) {
        userOperations.deactivateUser(id);
        return new ResponseEntity<>(new ApiResponse("Account Deactivated Successfully", true), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse> activateUser(@PathVariable Long id) {
        userOperations.activateUser(id);
        return new ResponseEntity<>(new ApiResponse("Account Activated Successfully", true), HttpStatus.OK);
    }

}
