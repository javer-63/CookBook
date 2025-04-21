package com.example.CookBook.services;

import com.example.CookBook.models.Category;
import com.example.CookBook.models.Recipe;
import com.example.CookBook.repos.CategoryRepository;
import com.example.CookBook.repos.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public List<Recipe> getAll(){
        return recipeRepository.findAll();
    }
    public List<Recipe> getAllByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            throw new EntityNotFoundException("Category not found: " + categoryName);
        }
        return recipeRepository.findAllByCategory(category);
    }
    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found recipe with id " + id));
    }
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new EntityNotFoundException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }
    public Recipe updateRecipe(Long id, Recipe newRecipe) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found recipe with id " + id));
        recipe.setTitle(newRecipe.getTitle());
        recipe.setDescription(newRecipe.getDescription());
        recipe.setDifficulty(newRecipe.getDifficulty());
        recipe.setCookingTime(newRecipe.getCookingTime());
        recipe.setCategory(newRecipe.getCategory());
        recipe.setRecipeText(newRecipe.getRecipeText());
        return recipeRepository.save(recipe);
    }
    public Recipe updatePartRecipe(Long id, Recipe recipeUpdates) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found recipe with id " + id));
        Optional.ofNullable(recipeUpdates.getTitle()).ifPresent(recipe::setTitle);
        Optional.ofNullable(recipeUpdates.getDescription()).ifPresent(recipe::setDescription);
        Optional.ofNullable(recipeUpdates.getDifficulty()).ifPresent(recipe::setDifficulty);
        Optional.ofNullable(recipeUpdates.getCategory()).ifPresent(recipe::setCategory);
        Optional.ofNullable(recipeUpdates.getRecipeText()).ifPresent(recipe::setRecipeText);
        if (recipeUpdates.getCookingTime() != 0) { // Для примитива int нужна отдельная проверка
            recipe.setCookingTime(recipeUpdates.getCookingTime());
        }
        return recipeRepository.save(recipe);
    }
}
