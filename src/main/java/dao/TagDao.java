package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Tag;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagDao {
    public int insert(Tag tag){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into tag(tag_name, film_id) values (?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setString(1, tag.getTag_name());
            pstmt.setInt(2, tag.getFilm_id());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

}
