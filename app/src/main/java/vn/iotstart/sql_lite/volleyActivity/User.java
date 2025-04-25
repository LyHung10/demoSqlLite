package vn.iotstart.sql_lite.volleyActivity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String images;

    public User(int id, String name, String email, String gender, String images) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.images = images;
    }

    // Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getImages() { return images; }
}
