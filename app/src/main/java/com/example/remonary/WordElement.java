package com.example.remonary;

public class WordElement {

    String title = "";
    String translate = "";
    String description = "";

    public WordElement(String title, String translate, String description) {
        this.title = title;
        this.translate = translate;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getTranslate() {
        return translate;
    }
    public String getDescription() {
        return description;
    }
}