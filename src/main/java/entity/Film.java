package entity;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

public class Film {
    private Integer id;
    private String name;
    private String description;
    private String director;
    private String photo;
    private Time duration;
    private String region;

    private Float avg_score;

    private List<String> tags;

    private String str_duration;

    public String getStr_duration() {
        return str_duration;
    }

    public void setStr_duration(String str_duration) {
        this.str_duration = str_duration;
    }

    public List<String> getTags() {
        return tags;
    }


    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private List<String> actors;

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }



    public Float getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(Float avg_score) {
        this.avg_score = avg_score;
    }

    public Film() {
    }

    public Film(Integer id, String name, String description, String director, String photo, Time duration, String region, Float avg_score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.director = director;
        this.photo = photo;
        this.duration = duration;
        this.region = region;
        this.avg_score = avg_score;
        if (this.duration != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            this.str_duration = dateFormat.format(duration);
        }


    }

    public Film(String name, String description, String director, String photo, Time duration, String region, Float avg_score) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.photo = photo;
        this.duration = duration;
        this.region = region;
        this.avg_score = avg_score;
        if (this.duration != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            this.str_duration = dateFormat.format(duration);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
