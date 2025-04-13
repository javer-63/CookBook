package com.example.CookBook.models;

import com.example.CookBook.enums.Difficulty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private int cookingTime;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(columnDefinition = "text")
    private String recipeText;

    public Recipe(String title, String description, Difficulty difficulty,
                  int cookingTime, Category category, String recipeText) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.cookingTime = cookingTime;
        this.category = category;
        this.recipeText = recipeText;
    }
}