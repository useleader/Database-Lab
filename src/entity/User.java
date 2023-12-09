package entity;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

public class User {
    private Integer id;
    private String name;
    private String password;
    private String photo;
    private String gender;
    private String description;
    private String email;


    public User(String name, String password, String photo, String gender, String description, String email) {
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.gender = gender;
        this.description = description;
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // id获取方法
    public Integer getId() {
        return id;
    }

    // id赋值方法
    public void setId(Integer id) {
        this.id = id;
    }

    // name获取方法
    public String getName() {
        return name;
    }

    // name赋值方法
    public void setName(String name) {
        this.name = name;
    }

    // password获取
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}