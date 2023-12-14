package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.ActorDao;
import dao.FilmDao;
import dao.PerformDao;
import dao.TagDao;
import entity.Actor;
import entity.Film;
import entity.Perform;
import entity.Tag;
import util.StringExtractor;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/movielist/insert_movie")
public class InsertServlet extends HttpServlet {

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


//        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String director = request.getParameter("director");
//        String photo = request.getParameter("photo");
        String actors = request.getParameter("performer");
        String tags = request.getParameter("type");
        Time duration = null;
        try {
            duration = Time.valueOf(request.getParameter("duration"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String region = request.getParameter("country");
        List<String> actorList = new ArrayList<String>();
        List<String> tagList = new ArrayList<String>();


//      Film 表修改
        FilmDao filmDao = new FilmDao();
        Film film = new Film();
        film.setName(name);
        film.setDescription(description);
        film.setDirector(director);
//        film.setPhoto(photo);
        film.setDuration(duration);
        film.setRegion(region);
        int count = filmDao.insert(film);
        // cnt = 1, success; cnt = 0, failure
        int film_id = filmDao.getMaxId();

        try {
            PerformDao performDao = new PerformDao();
            actorList = Arrays.asList(actors.split(","));
            for (String s : actorList) {
                ActorDao ad = new ActorDao();
                Actor actor = ad.selectByName(s);
                if (actor != null) {
                    Integer actor_id = actor.getId();
                    Perform p = new Perform(film_id, actor_id);
                    performDao.insert(p);
                }
            }
            TagDao tagDao = new TagDao();
            tagList = Arrays.asList(tags.split(","));
            for (String s : tagList) {
                Tag tag = new Tag(s, film_id);
                tagDao.insert(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String jsonStr = JSON.toJSONString(count);
            response.getWriter().write(jsonStr);
        } catch (JSONException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }
}