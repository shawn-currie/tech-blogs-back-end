package com.shawncurrie.techblogs.io.entity;

import com.shawncurrie.techblogs.shared.dto.BlogDTO;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "companies")
public class CompanyEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4327894732894792L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="url")
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
