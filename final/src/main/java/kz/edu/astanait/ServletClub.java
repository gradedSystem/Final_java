package kz.edu.astanait;

import kz.edu.astanait.controllers.ClubController;
import kz.edu.astanait.controllers.ClubModerController;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Moder;

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

@WebServlet(name = "ServletClub", urlPatterns = "/clubServlet")
public class ServletClub extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ClubController clubController = new ClubController();
        if(action.equals("show")){
            LinkedList<Club> clubsList  = (LinkedList<Club>) clubController.getAll();
            HttpSession clubSession = request.getSession();
            clubSession.setAttribute("clubs",clubsList);
            request.getRequestDispatcher("jsp/club/clubs.jsp").forward(request,response);
        }else if (action.equals("add")){
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            Club club = new Club.Builder().setClub(name,owner,moders,description,img_url).build();
            clubController.add(club);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("edit")){
            int club_id = Integer.parseInt(request.getParameter("club_id"));
            System.out.println(request.getParameter("club_id"));
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            Club club = new Club.Builder().setClub(name,owner,moders,description,img_url).setClub_id(club_id).build();
            clubController.update(club);
            System.out.println(club.getId()+club.getName()+club.getOwner());
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("delete")){
            ClubModerController clubModerController = new ClubModerController();
            int club_id = Integer.parseInt(request.getParameter("club_id"));
            Club club = new Club.Builder().setClub_id(club_id).build();
            clubModerController.deleteAll(club_id);
            clubController.delete(club);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
