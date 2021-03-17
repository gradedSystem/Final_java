package kz.edu.astanait;

import kz.edu.astanait.controllers.ClubController;
import kz.edu.astanait.controllers.ClubModerController;
import kz.edu.astanait.controllers.NewsController;
import kz.edu.astanait.controllers.NewsModerController;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Moder;
import kz.edu.astanait.models.News;
import kz.edu.astanait.models.User;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ServletNews", urlPatterns = "/newsServlet")
public class ServletNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        NewsController newsController = new NewsController();
        if(action.equals("show")){
            LinkedList<News> newsList  = (LinkedList<News>) newsController.getAll();
            HttpSession clubSession = request.getSession();
            clubSession.setAttribute("news",newsList);
            request.getRequestDispatcher("jsp/news/news.jsp").forward(request,response);
        }else if (action.equals("add")){
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            News news = new News.Builder().setNews(name,owner,moders,description,img_url).build();
            newsController.add(news);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("edit")){
            int news_id = Integer.parseInt(request.getParameter("news_id"));
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            News news = new News.Builder().setNews(name,owner,moders,description,img_url).setNews_id(news_id).build();
            newsController.update(news);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("delete")){
            NewsModerController newsModerController = new NewsModerController();
            int news_id = Integer.parseInt(request.getParameter("news_id"));
            News news = new News.Builder().setNews_id(news_id).build();
            newsModerController.deleteAll(news_id);
            newsController.delete(news);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
