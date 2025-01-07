package com.ecommerce.module.product;

import com.ecommerce.module.category.CategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "Product Operations", description = "Endpoints for handling product-related functionalities")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/memberId/{memberId}/categoryId/{categoryId}/product")
    @Operation(summary = "create new product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request, @PathVariable Long memberId, @PathVariable Long categoryId){
        ProductResponse product = service.createProduct(request, memberId, categoryId);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @PatchMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request, @PathVariable Long productId){

        ProductResponse response = service.updateProduct(request, productId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId){

        ProductResponse response = service.getProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){

        List<ProductResponse> response = service.getAllProduct();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
