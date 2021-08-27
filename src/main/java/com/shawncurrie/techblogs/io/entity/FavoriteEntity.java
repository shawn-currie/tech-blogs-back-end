package com.shawncurrie.techblogs.io.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity()
@Table(name = "favorites")
public class FavoriteEntity  implements Serializable {
    @Serial
    private static final long serialVersionUID = 4327894732894792L;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name="user")
    private int user;

    @Column(nullable = false, name="blog")
    private int blog;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBlog() {
        return blog;
    }

    public void setBlog(int blog) {
        this.blog = blog;
    }

}
