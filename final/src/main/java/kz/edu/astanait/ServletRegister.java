package kz.edu.astanait;

import com.google.gson.Gson;
import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "ServletRegister",urlPatterns = "/register")
public class ServletRegister extends HttpServlet {
    UserController userController = new UserController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String year = request.getParameter("year");
        String major = request.getParameter("major");
        String group = request.getParameter("group");
        System.out.println(year);
        System.out.println(major);
        System.out.println(group);
        User user = new User.Builder().setUser(fname,lname,email,password,"student",year,major,group).build();
        userController.add(user);
        response.sendRedirect(getServletContext().getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> message = new HashMap<>();
        if (request.getParameter("action").equals("chekEmail")){
            message.put("message", "empty");
            String email = request.getParameter("email");
            email = email.toLowerCase();
            if (!email.isEmpty()) {
                try {
                    if (userController.checkEmail(email)) {
                        message.put("message", "Email already taken. Try another email");
                    } else {
                        message.put("message", "success");
                    }
                } catch (SQLException throwables) {
                    message.put("message", "Sql error");
                    throwables.printStackTrace();
                }
            }
            String json = new Gson().toJson(message);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else if (request.getParameter("action").equals("checkYear")){
            message.put("yearMessage", "empty");
            int year = Integer.parseInt(request.getParameter("year"));
            if (year != 0) {
                if (year < 2019) {
                    message.put("yearMessage", "Year is too early. Put right year");
                } else if(year > Year.now().getValue()){
                    message.put("yearMessage", "Year is in the future. Put right year");
                } else {
                    message.put("yearMessage", "success");
                }
            }
            String json = new Gson().toJson(message);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }


}
