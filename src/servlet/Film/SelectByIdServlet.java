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
import java.util.List;

@WebServlet("/movielist/selectById")
public class SelectByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));

        FilmDao filmDao = new FilmDao();
        Film film = filmDao.selectById(id);
        // TODO
        request.setAttribute("film_attribute", film);
        // TODO 路径修改
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}

