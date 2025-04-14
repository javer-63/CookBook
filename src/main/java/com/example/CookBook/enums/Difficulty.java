package com.example.CookBook.enums;

public enum Difficulty {
    EASY("Легко"), MEDIUM("Средне"), HARD("Сложно");
    private String rus;

    Difficulty(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }
}
