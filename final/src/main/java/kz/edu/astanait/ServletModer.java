package kz.edu.astanait;

import kz.edu.astanait.controllers.ClubModerController;
import kz.edu.astanait.controllers.EventModerController;
import kz.edu.astanait.controllers.NewsModerController;
import kz.edu.astanait.models.Moder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletModer", urlPatterns = "/moderServlet")
public class ServletModer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int id = Integer.parseInt(request.getParameter("id"));
        Moder moder = null;
        if (action.equals("clubModer")){
            moder = new Moder.Builder().withId(user_id).clubMod(id).build();
            System.out.println(moder.getClubId()+moder.getId());
            ClubModerController clubModerController = new ClubModerController();
            clubModerController.add(moder);
        }else if (action.equals("eventModer")){
            moder = new Moder.Builder().withId(user_id).eventMod(id).build();
            EventModerController eventModerController = new EventModerController();
            eventModerController.add(moder);
        }else if (action.equals("newsModer")){
            moder = new Moder.Builder().withId(user_id).newsMod(id).build();
            NewsModerController newsModerController = new NewsModerController();
            newsModerController.add(moder);
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
