package kz.edu.astanait;

import kz.edu.astanait.controllers.ClubController;
import kz.edu.astanait.controllers.ClubModerController;
import kz.edu.astanait.controllers.EventController;
import kz.edu.astanait.controllers.EventModerController;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.Moder;
import kz.edu.astanait.models.User;

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

@WebServlet(name = "ServletEvent", urlPatterns = "/eventServlet")
public class ServletEvent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EventController eventController = new EventController();
        if(action.equals("show")){
            LinkedList<Event> eventList  = (LinkedList<Event>) eventController.getAll();
            HttpSession clubSession = request.getSession();
            clubSession.setAttribute("events",eventList);
            request.getRequestDispatcher("jsp/event/events.jsp").forward(request,response);
        }else if (action.equals("add")){
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            Event event = new Event.Builder().setEvent(name,owner,moders,description,img_url).build();
            eventController.add(event);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("edit")){
            int event_id = Integer.parseInt(request.getParameter("event_id"));
            String name = request.getParameter("name");
            String owner = request.getParameter("owner");
            String description = request.getParameter("description");
            String img_url = request.getParameter("img_url");
            List<Moder> moders =null;
            Event event = new Event.Builder().setEvent(name,owner,moders,description,img_url).setEvent_id(event_id).build();
            System.out.println(event);
            eventController.update(event);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else if (action.equals("delete")){
            EventModerController eventModerController = new EventModerController();
            int event_id = Integer.parseInt(request.getParameter("event_id"));
            Event event = new Event.Builder().setEvent_id(event_id).build();
            eventModerController.deleteAll(event_id);
            eventController.delete(event);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
