package com.ecommerce.module.product;


import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.module.user.value.UserResponse;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public ProductResponse createProduct(ProductRequest request, Long memberId, Long categoryId){

        User userDetails = userRepository.findByMemberId(memberId);

        if(userDetails == null ){
            throw new ResourceNotFoundException("User details not found for ","member id: ", memberId);
        }
        Category category = categoryRepository.findById(Math.toIntExact(categoryId))
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setSeller(userDetails.getMemberId());
        product.setCategory_id(category);
        Product save = productRepository.save(product);

        ProductResponse.ProductResponseBuilder responseBuilder = ProductResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .category_id(save.getCategory_id());

        if (userDetails.getMemberId().equals(save.getSeller())){
            UserResponse userResponse = UserResponse.builder()
                    .name(userDetails.getName())
                    .memberId(userDetails.getMemberId())
                    .email(userDetails.getEmail())
                    .build();
            responseBuilder.seller(userResponse);
        }
        return responseBuilder.build();
    }


    public ProductResponse updateProduct(ProductRequest request, Long productId){

        Product product = productRepository.findById(Math.toIntExact(productId)).orElseThrow(() -> new ResourceNotFoundException("Product data not found for ", "product id: ", productId));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setPrice(request.price());

        Product save = productRepository.save(product);

        return ProductResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .price(save.getPrice())
                .quantity(save.getQuantity()).build();
    }


    public ProductResponse getProduct(Long productId){
        Product product = productRepository.findById(Math.toIntExact(productId)).orElseThrow(() -> new ResourceNotFoundException("Product data not found for ", "product id: ", productId));

        User userDetails = userRepository.findByMemberId(product.getSeller());

        if(userDetails == null ){
            throw new ResourceNotFoundException("User details not found for ","member id: ", product.getSeller());
        }

        UserResponse userResponse = UserResponse.builder()
                .name(userDetails.getName())
                .memberId(userDetails.getMemberId())
                .email(userDetails.getEmail())
                .build();
        return ProductResponse.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity()).seller(userResponse).build()
                ;

    }

    public List<ProductResponse> getAllProduct(){
        List<Product> allProducts = (List<Product>) productRepository.findAll();
        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();
        for (Product product : allProducts) {
            User userDetails = userRepository.findByMemberId(product.getSeller());

            if (userDetails == null) {
                throw new ResourceNotFoundException("User details not found for ", "member id: ", product.getSeller());
            }

            userResponse.name(userDetails.getName())
                    .memberId(userDetails.getMemberId())
                    .email(userDetails.getEmail())
                    .build();
        }
        return allProducts.stream().map(product -> ProductResponse.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity()).seller(userResponse.build()).build()).collect(Collectors.toList());

    }
}
