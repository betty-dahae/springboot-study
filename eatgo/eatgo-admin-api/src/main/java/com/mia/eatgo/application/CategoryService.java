package com.mia.eatgo.application;

import com.mia.eatgo.domain.Category;
import com.mia.eatgo.domain.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category addCategory(String name) {
        Category category = Category.builder().name(name).build();

        return categoryRepository.save(category);
    }
}
