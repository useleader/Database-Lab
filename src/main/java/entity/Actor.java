package entity;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

public class Actor {
    private Integer id;
    private String name;
    private String photo;
    private Integer age;

    // 无参数的构造函数
    public Actor() {
    }

    public Actor(Integer id, String name, String photo, Integer age) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.age = age;
    }

    // 无id的构造函数
    public Actor(String name, String photo, Integer age) {
        this.name = name;
        this.photo = photo;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}

