package com.shawncurrie.techblogs.io.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity()
@Table(name = "favorites")
@IdClass(FavoriteId.class)
public class FavoriteEntity  implements Serializable {
    @Serial
    private static final long serialVersionUID = 4327894732894792L;

    @Id
    private int user;

    @Id
    private int blog;

    public FavoriteEntity(int user, int blog) {
        this.user = user;
        this.blog = blog;
    }

    public FavoriteEntity() {
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
