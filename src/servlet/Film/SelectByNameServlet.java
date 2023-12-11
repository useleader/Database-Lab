package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.FilmDao;

import entity.Film;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/movielist/selectByName")
public class SelectByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
//        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        FilmDao filmDao = new FilmDao();
        List<Film> films = filmDao.selectByName(name);
        // TODO
        request.setAttribute("filmList", films);
        // TODO 路径修改
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}
