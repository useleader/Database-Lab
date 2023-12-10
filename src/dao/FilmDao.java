package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Film;
import entity.User;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
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
                //TODO 这里Duration的处理不知道是否可行
                Film film = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Duration.parse(rs.getString(6)),
                        rs.getString(7));
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
            //TODO 这里Duration的处理不知道是否可行
            pstmt.setString(5, film.getDuration().toString());
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
                //TODO 这里Duration的处理不知道是否可行
                film = new Film(rs.getInt(1), // 从结果集获取第一列的整数值
                        rs.getString(2), // 从结果集获取第二列的字符串值，后面以此类推
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Duration.parse(rs.getString(6)),
                        rs.getString(7));
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
            //TODO 这里Duration的处理不知道是否可行
            pstmt.setString(5, film.getDuration().toString());
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
}
