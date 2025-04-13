package com.example.CookBook.services;

import com.example.CookBook.repos.CategoryRepository;
import com.example.CookBook.repos.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
}
