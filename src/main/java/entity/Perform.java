package entity;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

public class Perform {
    private Integer film_id;
    private Integer actor_id;

    public Perform(Integer film_id, Integer actor_id) {
        this.film_id = film_id;
        this.actor_id = actor_id;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }
}
