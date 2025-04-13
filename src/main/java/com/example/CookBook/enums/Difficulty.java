package com.example.CookBook.enums;

public enum Difficulty {
    EASY("легко"), MEDIUM("средне"), HARD("сложно");
    private String rus;

    Difficulty(String rus) {
        this.rus = rus;
    }
}
