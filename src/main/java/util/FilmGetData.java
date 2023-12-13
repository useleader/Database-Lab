package util;/*
 * @author   yan
 * @time     2023/12/13
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmGetData {
    public List<String> getActors(Integer filmId, Connection conn){
        List<String> actors = new ArrayList<>();
        try {
            //                找演员
            PreparedStatement pstmt2 = null;
            ResultSet rs2 = null;
            pstmt2 = conn.prepareStatement("select actor.name from actor join perform on actor.actor_id = perform.actor_id join film on perform.film_id = film.film_id where film.film_id=?");
            pstmt2.setInt(1, filmId);
            rs2 = pstmt2.executeQuery();
            while(rs2.next()){
                String actor_name = rs2.getString(1);
                actors.add(actor_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<String> getTags(Integer filmId, Connection conn) {
        List<String> tags = new ArrayList<>();
        try {
            PreparedStatement pstmt3 = null;
            ResultSet rs3 = null;

            pstmt3 = conn.prepareStatement("select tag_name from tag join film on tag.film_id = film.film_id where film.film_id=?");
            pstmt3.setInt(1,filmId);
            rs3 = pstmt3.executeQuery();
            while(rs3.next()){
                String tag_name = rs3.getString(1);
                tags.add(tag_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }


}
