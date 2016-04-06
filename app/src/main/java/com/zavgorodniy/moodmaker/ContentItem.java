package com.zavgorodniy.moodmaker;

/**
 * Created by nick on 05.04.16.
 */
public class ContentItem {
    private String title;
    private String name;
    private String image;
    private String description;

    public ContentItem (String title, String image, String name, String description) {
        this.title = title;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
