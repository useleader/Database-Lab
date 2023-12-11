package entity;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

public class Tag {
    private String tag_name;
    private Integer film_id;

    public Tag(String tag_name, Integer film_id) {
        this.tag_name = tag_name;
        this.film_id = film_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }
}
