package com.example.remonary;

import java.io.Serializable;

public class WordElement implements Serializable {

    String title = "";
    String translate = "";
    String description = "";

    public WordElement() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordElement that = (WordElement) o;

        if (!title.equals(that.title)) return false;
        if (!translate.equals(that.translate)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + translate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}