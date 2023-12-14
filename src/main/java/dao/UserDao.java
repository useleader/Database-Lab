package dao;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.User;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    public int login(String username, String password) {
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("select password from user where name=?;");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            String rel_pwd = rs.getString(1);
            if (rel_pwd.equals(password)) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int signup(User u) {
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into user(name, password) values (?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPassword());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }
    public List<User> selectAll(){
        List<User> list = new ArrayList<>();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from user;");
            rs = pstmt.executeQuery(); // 执行SQL语句，返回结果集
            // 使用rs.next()方法一行一行读取查询结果
            while(rs.next()){
                User u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(User u){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into user(name, password, photo, gender, description, email) values (?, ?, ?, ?, ?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getPhoto());
            pstmt.setString(4, u.getGender());
            pstmt.setString(5, u.getDescription());
            pstmt.setString(6, u.getEmail());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

    public  User selectById(int id){
        User u = new User();
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("select * from user where user_id = ?");
            pstmt.setInt(1, id); // 将占位符 ？的值设置为 id
            rs = pstmt.executeQuery();
            while (rs.next()){
                u = new User(rs.getInt(1), // 从结果集获取第一列的整数值
                        rs.getString(2), // 从结果集获取第二列的字符串值，后面以此类推
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return u;
    }

    public int update(User u){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement("update user set name = ?, password = ?, email = ?, photo = ?, gender = ?, description = ? where user_id = ?");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getEmail());
            pstmt.setString(4, u.getPhoto());
            pstmt.setString(5, u.getGender());
            pstmt.setString(6, u.getDescription());
            pstmt.setInt(7, u.getId());
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
}
