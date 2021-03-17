package kz.edu.astanait;

import com.google.gson.Gson;
import kz.edu.astanait.REST.RestClient;
import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/search")
public class ServletSearch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RestClient restClient = new RestClient();
        User user = null;
        String json = null;
        HttpSession session = request.getSession();
        if(action.equals("byGroup")){
            List<User> groupList = restClient.getByGroup(request.getParameter("param"));
            json = new Gson().toJson(groupList);
        }else if(action.equals("byYear")){
            List<User> yearList = restClient.getByYear(request.getParameter("param"));
            json = new Gson().toJson(yearList);
        }else if(action.equals("byMajor")){
            List<User> majorList = restClient.getByMajor(request.getParameter("param"));
            json = new Gson().toJson(majorList);
        }else if(action.equals("byName")){
            List<User> nameList = restClient.getByName(request.getParameter("param"));
            json = new Gson().toJson(nameList);
        }else if(action.equals("showAll")){
            UserController userController = new UserController();
            session.setAttribute("users",userController.getAll());
            request.getRequestDispatcher("/jsp/users.jsp").forward(request,response);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(json);
        if (json != null) {
            response.getWriter().write(json);
        }else{
            response.getWriter().write("No such");
        }
    }
}
