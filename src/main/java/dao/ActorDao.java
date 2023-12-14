package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Actor;
import entity.User;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {


    public List<Actor> selectAll(){
        List<Actor> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from actor;");
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                Actor actor = new Actor(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                list.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(Actor u){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into actor(name, photo) values (?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPhoto());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public  Actor selectById(int id){
        Actor actor = new Actor();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from actor where actor_id = ?");
            pstmt.setInt(1, id); // 将占位符 ？的值设置为 id
            rs = pstmt.executeQuery();
            while (rs.next()){
                actor = new Actor(rs.getInt(1), // 从结果集获取第一列的整数值
                        rs.getString(2), // 从结果集获取第二列的字符串值，后面以此类推
                        rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return actor;
    }

    public int update(Actor u){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("update actor set name = ?, photo = ? where actor_id = ?");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPhoto());
            pstmt.setInt(3, u.getId());
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
            pstmt = conn.prepareStatement("delete from user where user_id = ?");
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public  Actor selectByName(String name){
        Actor actor = new Actor();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from actor where name = ?");
            pstmt.setString(1, name); // 将占位符 ？的值设置为 id
            rs = pstmt.executeQuery();
            while (rs.next()){
                actor = new Actor(rs.getInt(1), // 从结果集获取第一列的整数值
                        rs.getString(2), // 从结果集获取第二列的字符串值，后面以此类推
                        rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return actor;
    }
}
