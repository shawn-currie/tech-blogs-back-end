package com.shawncurrie.techblogs.io.entity;

import java.io.Serializable;
import java.util.Objects;

public class FavoriteId implements Serializable {
    private int user;
    private int blog;

    public FavoriteId(int user, int blog) {
        this.user = user;
        this.blog = blog;
    }

    public FavoriteId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return user == that.user && blog == that.blog;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, blog);
    }
}
