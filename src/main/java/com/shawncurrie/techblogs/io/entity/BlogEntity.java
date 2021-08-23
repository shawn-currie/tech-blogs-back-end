package com.shawncurrie.techblogs.io.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "blogs")
public class BlogEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4327894732894792L;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name="title")
    private String title;

    @Column(nullable = false, name="url")
    private String url;

    @Column(nullable = false, name="company_id")
    private int companyId;

    @Column(nullable = false, name="date")
    private String date;

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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}