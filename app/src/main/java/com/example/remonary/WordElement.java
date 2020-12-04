package com.example.remonary;

import java.io.Serializable;

public class WordElement implements Serializable, Comparable {

    long id;
    String title;
    String translate;
    String description;

    public WordElement() {
    }

    public WordElement(long id){
        this.id = id;
    }

    public WordElement(long id, String title, String translate, String description) {
        this.id = id;
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}