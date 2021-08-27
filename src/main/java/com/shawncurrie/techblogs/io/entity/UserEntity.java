package com.shawncurrie.techblogs.io.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity()
@Table(name = "users")
public class UserEntity  implements Serializable {
    @Serial
    private static final long serialVersionUID = 4327894732894792L;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
