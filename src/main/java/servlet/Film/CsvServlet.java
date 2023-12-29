package servlet.Film;/*
 * @author   yan
 * @time     2023/12/19
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.FilmDao;
import dao.UserDao;
import entity.Film;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import util.DBConnectionUtil;
import java.sql.Connection;
import entity.Perform;
import entity.Tag;
import entity.User;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/export") // 指定特定的URL路径与该Servlet类关联
public class CsvServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有的ip的可以访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // 允许访问的方式
        response.setHeader("Access-Control-Max-Age", "3600");  // 设置超时时间
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"exported_data.csv\"");

        try (PrintWriter writer = response.getWriter();
             Connection conn = DBConnectionUtil.getConnection()) {

            // 查询电影和演员的关系数据
            String query = "SELECT film.name AS movie, actor.name AS actor, " +
            "CASE WHEN perform.actor_id IS NULL THEN 0 ELSE 1 END AS is_participating " +
            "FROM film " +
            "CROSS JOIN actor " +
            "LEFT JOIN perform ON film.film_id = perform.film_id AND actor.actor_id = perform.actor_id " +
            "LIMIT 50000";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // 获取所有演员的列表
                List<String> actors = new ArrayList<>();
                List<String> movies = new ArrayList<>();
                while (resultSet.next()) {
                    String actorName = resultSet.getString("actor");
                    String movieTitle = resultSet.getString("movie");

                    if (!actors.contains(actorName)) {
                        actors.add(actorName);
                    }

                    if (!movies.contains(movieTitle)) {
                        movies.add(movieTitle);
                    }
                }
                writer.print("Movie,");
                for (String actor : actors) {
                    writer.print(actor + ",");
                }
                writer.println();

                // 循环遍历结果集并写入CSV文件，行为电影
                for (String movie : movies) {
                    writer.print(movie + ",");

                    for (String actor : actors) {
                        // 在这里查找是否参演
                        // 如果参演，则写入 1，否则写入 0
                        // 你可以根据实际需要调整这部分逻辑
                        // 这里仅作示范
                        // 注意：这里的逻辑可能需要根据数据库中的具体情况来调整
                        int isParticipating = this.performCheck(resultSet, movie, actor);
                        writer.print(isParticipating + ",");
                    }
                    writer.println();
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
        }
            catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error exporting data to CSV");
        }

    }
    private int performCheck(ResultSet resultSet, String movie, String actor) throws SQLException {
        resultSet.beforeFirst(); // 将结果集的指针移到开头
        while (resultSet.next()) {
            String movieTitle = resultSet.getString("movie");
            String actorName = resultSet.getString("actor");
            int isParticipating = resultSet.getInt("is_participating");

            if (movie.equals(movieTitle) && actor.equals(actorName)) {
                return isParticipating;
            }
        }
        return 0; // 默认为未参演
    }
}

