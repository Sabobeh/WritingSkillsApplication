package com.example.writingskillsapplication;

import androidx.annotation.NonNull;

public class Resource {
    private String link;
    private String level;

    public Resource(String link, String level) {
        this.link = link;
        this.level = level;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @NonNull
    @Override
    public String toString() {
        return link;
    }
}
