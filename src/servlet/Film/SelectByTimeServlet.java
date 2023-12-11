package servlet.Film;/*
 * @author   yan
 * @time     2023/12/11
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import dao.FilmDao;
import dao.UserDao;
import entity.Film;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

@WebServlet("/movielist/selectByTime")
public class SelectByTimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Time min_time = Time.valueOf(request.getParameter("min_time"));
        Time max_time = Time.valueOf(request.getParameter("max_time"));

        FilmDao filmDao = new FilmDao();
        List<Film> films = filmDao.selectByTime(min_time, max_time);
        // TODO
        request.setAttribute("filmList", films);
        // TODO 路径修改
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}
