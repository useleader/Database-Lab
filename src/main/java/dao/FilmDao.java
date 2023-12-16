package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Film;
import util.DBConnectionUtil;
import util.FilmGetData;

import java.beans.Introspector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {


    public List<Film> selectAll(){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film;");
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集

            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));

                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(Film film){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into film(name, description, director, photo, duration, region) values (?, ?, ?, ?, ?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setString(1, film.getName());
            pstmt.setString(2, film.getDescription());
            pstmt.setString(3, film.getDirector());
            pstmt.setString(4, film.getPhoto());
            pstmt.setTime(5, film.getDuration());
            pstmt.setString(6, film.getRegion());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public  Film selectById(int id){
        Film film = new Film();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where film_id = ?");
            pstmt.setInt(1, id); // 将占位符 ？的值设置为 id
            rs = pstmt.executeQuery();
            while (rs.next()){
                film = new Film(rs.getInt(1), // 从结果集获取第一列的整数值
                        rs.getString(2), // 从结果集获取第二列的字符串值，后面以此类推
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return film;
    }

    public int update(Film film){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("update film set name = ?, description = ?, director = ?, photo = ?, duration = ?, region = ? where film_id = ?");
            pstmt.setString(1, film.getName());
            pstmt.setString(2, film.getDescription());
            pstmt.setString(3, film.getDirector());
            pstmt.setString(4, film.getPhoto());
            pstmt.setTime(5, film.getDuration());
            pstmt.setString(6, film.getRegion());
            pstmt.setInt(7, film.getId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public int delete(int id){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("delete from film where film_id = ?");
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public List<Film> selectByName(String name){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where name= ?;");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> selectByRegion(String region){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where region= ?;");
            pstmt.setString(1, region);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> selectByDirector(String director){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where director= ?;");
            pstmt.setString(1, director);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> selectByTag(String tag){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film natural join tag where tag_name = ?;");
            pstmt.setString(1, tag);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> selectByTime(Time min_time, Time max_time){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where duration between ? AND ?;");
            pstmt.setTime(1, min_time);
            pstmt.setTime(2, max_time);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> selectByScore(Float min_score, Float max_score){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from film where avg_score >= ? AND avg_score <= ?;");
            pstmt.setFloat(1, min_score);
            pstmt.setFloat(2, max_score);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //TODO 这里正常是多选tag,待扩展
    public List<Film> selectByCombine(String tag, String region, Float min_score, Float max_score, Time min_duration, Time max_duration){
        List<Film> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // score没录入
            pstmt = conn.prepareStatement("select distinct * from film natural join tag natural join score where tag_name = ? and region = ? and (duration between ? and ?) and (number between ? and ?);");
            pstmt.setString(1, tag);
            pstmt.setString(2, region);
            pstmt.setTime(3, min_duration);
            pstmt.setTime(4,max_duration);
            pstmt.setFloat(5,min_score);
            pstmt.setFloat(6,max_score);
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTime(6),
                        rs.getString(7),
                        rs.getFloat(8));
                FilmGetData perform = new FilmGetData();
                film.setActors(perform.getActors(film.getId(), conn));
                film.setTags(perform.getTags(film.getId(), conn));
                list.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getMaxId() {
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer maxId = 0;
        try {
            pstmt = conn.prepareStatement("select max(film_id) from film;");
            rs = pstmt.executeQuery();
            if(rs.next()) {
                maxId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
