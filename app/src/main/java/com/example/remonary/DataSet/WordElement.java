package com.example.remonary.DataSet;

import java.io.Serializable;

public class WordElement implements Serializable{

    long id;
    String title;
    String languageMark;
    String translate;
    String description;

    double weight;

    public WordElement() {
    }

    public WordElement(long id, String title, String languageMark, String translate, String description, double weight) {
        this.id = id;
        this.title = title;
        this.languageMark = languageMark;
        this.translate = translate;
        this.description = description;
        this.weight = weight;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLanguageMark(String languageMark) {
        this.languageMark = languageMark;
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

    public long getId(){ return id; }
    public String getTitle() {
        return title;
    }
    public String getLanguageMark() {
        return languageMark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordElement that = (WordElement) o;

        if (id != that.id) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (languageMark != null ? !languageMark.equals(that.languageMark) : that.languageMark != null)
            return false;
        if (translate != null ? !translate.equals(that.translate) : that.translate != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (languageMark != null ? languageMark.hashCode() : 0);
        result = 31 * result + (translate != null ? translate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}