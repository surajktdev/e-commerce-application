package com.ecommerce.module.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryRequest createCategory(CategoryRequest request){

        Category category = new Category();
        category.setName(request.getName());

        Category save = categoryRepository.save(category);
        return CategoryRequest.builder().name(save.getName()).build();
    }

    public List<CategoryRequest> getAllCategory(){
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();;
        return allCategories.stream().map(data -> CategoryRequest.builder().name(data.getName()).build()).toList();
    }
}
