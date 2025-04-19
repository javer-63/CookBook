package com.example.CookBook.controllers;

import com.example.CookBook.models.Category;
import com.example.CookBook.models.Recipe;
import com.example.CookBook.repos.CategoryRepository;
import com.example.CookBook.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestRecipeController {
    private final RecipeService recipeService;
    private final CategoryRepository categoryRepository;
    @GetMapping("/recipes")
    public List<Recipe> recipes(@RequestParam(required = false) String category){
        if (category != null && !category.isEmpty()) {
            return recipeService.getAllByCategory(category);
        } else {
            return recipeService.getAll();
        }
    }
    @GetMapping("/categories")
    public List<Category> categories(){
        return categoryRepository.findAll();
    }
    @GetMapping("/recipes/{id}")
    public Recipe recipe(@PathVariable Long id){
        return recipeService.getRecipeById(id);
    }
    @PostMapping("/recipes/add")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }
    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "Recipe with id " + id + " deleted successfully!";
    }
}