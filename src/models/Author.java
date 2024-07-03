package models;

import java.time.LocalDateTime;

public class Author{
    private static int count = 0;
    private int id;
    private String name;

    public Author(String name) {
        this.id = ++count;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return "{Id=" + id +
                ", Name=" + name + "}\n";
    }
}
