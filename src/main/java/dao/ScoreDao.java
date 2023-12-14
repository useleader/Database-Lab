package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Score;
import entity.Tag;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreDao {
    public int insert(Score score){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into score(user_id, film_id, number) values (?, ?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setInt(1, score.getUser_id());
            pstmt.setInt(2, score.getFilm_id());
            pstmt.setFloat(3, score.getNumber());
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
