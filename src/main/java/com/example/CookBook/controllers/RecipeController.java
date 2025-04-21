package com.example.CookBook.controllers;

import com.example.CookBook.enums.Difficulty;
import com.example.CookBook.models.Category;
import com.example.CookBook.models.Recipe;
import com.example.CookBook.repos.CategoryRepository;
import com.example.CookBook.services.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryRepository categoryRepository;
    @GetMapping("/")
    public String redirect(){
        return "redirect:/recipes";
    }
    @GetMapping("/recipes")
    public String recipes(@RequestParam(required = false) String category, Model model){
        if (category != null && !category.isEmpty()) {
            model.addAttribute("recipes", recipeService.getAllByCategory(category));
        } else {
            model.addAttribute("recipes", recipeService.getAll());
        }
        model.addAttribute("categories", categoryRepository.findAll());
        return "recipes";
    }
    @GetMapping("/recipes/{id}")
    public String recipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe";
    }
    @GetMapping("/recipes/add")
    public String addForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("difficulties", Difficulty.values());
        return "add-recipe";
    }

    @PostMapping("/recipes/add")
    public String addRecipe(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam Difficulty difficulty,
                            @RequestParam int cookingTime,
                            @RequestParam(name = "category") Long categoryId,
                            @RequestParam String recipeText) {
        Recipe recipe = new Recipe(title, description, difficulty, cookingTime,
                categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found")),
                recipeText);
        recipeService.createRecipe(recipe);
        return "redirect:/recipes";
    }
    @PostMapping("/recipes/{id}/delete")
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }
    @GetMapping("/recipes/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("difficulties", Difficulty.values());
        return "edit-recipe";
    }
    @PostMapping("/recipes/{id}/edit")
    public String updateRecipe(@PathVariable Long id,
                               @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam Difficulty difficulty,
                               @RequestParam int cookingTime,
                               @RequestParam(name = "category") Long categoryId,
                               @RequestParam String recipeText) {
        recipeService.updateRecipe(id, new Recipe(title, description, difficulty, cookingTime,
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found")),
                recipeText));
        return "redirect:/recipes/" + id;
    }
}
