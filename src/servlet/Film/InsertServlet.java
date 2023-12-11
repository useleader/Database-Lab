package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import dao.FilmDao;
import entity.Film;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;


@WebServlet("/movielist/insert_movie")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String director = request.getParameter("director");
        String photo = request.getParameter("photo");
        Time duration = Time.valueOf(request.getParameter("duration"));
        String region = request.getParameter("region");


        Film film = new Film();

//        user.setId(Integer.valueOf(id));
        film.setName(name);
        film.setDescription(description);
        film.setDirector(director);
        film.setPhoto(photo);
        film.setDuration(duration);
        film.setRegion(region);


        //快捷键是ctrl+alt+v
        FilmDao filmDao = new FilmDao();
        //调用添加接口
        int count = filmDao.insert(film);
        String str=null;
        if(count>0){
            str="添加新电影信息成功";
        }else{
            str="添加新电影信息失败";
        }

        PrintWriter out = response.getWriter();
        out.print("<script>");
        out.print("alert('" +str+ "');");//漏了一个分号
        //TODO 这里的href 还不确定应该写什么
        out.print("location.href='selectAll'");
        out.print("</script>");
        out.close();
    }
}