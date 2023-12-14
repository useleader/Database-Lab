package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.FilmDao;
import entity.Film;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.FilmDao;
import entity.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

@WebServlet("/movielist/combining")
public class SelectByCombiningServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有的ip的可以访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // 允许访问的方式
        response.setHeader("Access-Control-Max-Age", "3600");  // 设置超时时间
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setContentType("application/json; charset=utf-8");


        String tag = request.getParameter("type");
        String region = request.getParameter("country");
        Float min_score = Float.valueOf(request.getParameter("min_score"));
        Float max_score = Float.valueOf(request.getParameter("max_score"));
        Time min_duration = Time.valueOf(request.getParameter("min_duration"));
        Time max_duration = Time.valueOf(request.getParameter("max_duration"));
        FilmDao filmDao = new FilmDao();
        List<Film> films = filmDao.selectByCombine(tag, region, min_score, max_score, min_duration, max_duration);

        try {
            String jsonStr = JSON.toJSONString(films);
            response.getWriter().write(jsonStr);
        } catch (JSONException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }
}
