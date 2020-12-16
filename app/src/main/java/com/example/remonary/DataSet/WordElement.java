package com.example.remonary.DataSet;

import java.io.Serializable;

public class WordElement implements Serializable{

    long id;
    String title;
    String translate;
    String description;
    String languageMark;

    double weight;

    public WordElement() {
    }

    public WordElement(long id){
        this.id = id;
        this.weight = 0;
    }

    public WordElement(long id, String title, String translate, String description, double weight) {
        this.id = id;
        this.title = title;
        this.translate = translate;
        this.description = description;
        this.weight = weight;
    }

    public WordElement(String title, String translate, String description, String languageMark) {
        this.title = title;
        this.translate = translate;
        this.description = description;
        this.weight = 0;
        this.languageMark = languageMark;
    }

    public WordElement(String title, String translate, String languageMark) {
        this.title = title;
        this.translate = translate;
        this.languageMark = languageMark;
    }

    public void setId(long id) {
        this.id = id;
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
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setLanguageMark(String languageMark) {
        this.languageMark = languageMark;
    }

    public long getId(){ return id; }
    public String getTitle() {
        return title;
    }
    public String getTranslate() {
        return translate;
    }
    public String getDescription() {
        return description;
    }
    public double getWeight() {
        return weight;
    }
    public String getLanguageMark() {
        return languageMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordElement that = (WordElement) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (translate != null ? !translate.equals(that.translate) : that.translate != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (translate != null ? translate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}