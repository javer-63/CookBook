package com.example.CookBook.controllers;

import com.example.CookBook.repos.CategoryRepository;
import com.example.CookBook.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
}
