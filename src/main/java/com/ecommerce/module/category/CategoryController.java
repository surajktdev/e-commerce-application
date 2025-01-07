package com.ecommerce.module.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/category")
@Tag(name = "Category Operations", description = "Endpoints for handling category-related functionalities")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/")
    @Operation(summary = "create new category")
    public ResponseEntity<CategoryRequest> createCategory(@RequestBody CategoryRequest request){
        CategoryRequest category = service.createCategory(request);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    @GetMapping("/")
    @Operation(summary = "get all category")
    public ResponseEntity<List<CategoryRequest>> getMethodName() {
        return ResponseEntity.ok(service.getAllCategory());

    }
    
}
