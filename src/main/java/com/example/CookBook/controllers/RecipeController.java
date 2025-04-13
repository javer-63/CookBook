package com.example.CookBook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
