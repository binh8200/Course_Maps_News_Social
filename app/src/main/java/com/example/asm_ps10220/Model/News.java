package com.example.asm_ps10220.Model;

public class News {
    private String title;
    private String link;

    public News() {
    }

    public News(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "News: " +
                "" + title + '\'';
    }
}
