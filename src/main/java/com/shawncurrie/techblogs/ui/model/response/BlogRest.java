package com.shawncurrie.techblogs.ui.model.response;

public class BlogRest {
    private int id;
    private String title;
    private String url;
    private String date;
    private boolean favorite;
    private CompanyRest company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public CompanyRest getCompany() {
        return company;
    }

    public void setCompany(CompanyRest company) {
        this.company = company;
    }
}