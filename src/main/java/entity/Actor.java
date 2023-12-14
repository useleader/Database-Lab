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


    // 无参数的构造函数
    public Actor() {
    }

    public Actor(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Actor(String name, String photo) {
        this.name = name;
        this.photo = photo;
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


}

