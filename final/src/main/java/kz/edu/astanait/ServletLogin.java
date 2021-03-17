package kz.edu.astanait;

import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletLogin",urlPatterns = "/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("pass");
        UserController userController = new UserController();

        User user = null;
        try {
            user = userController.checkLogin(email, password);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*60);
            if (user == null) {
                String message = "Invalid email or password! Try again";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
                response.sendRedirect(getServletContext().getContextPath() + "/jsp/login.jsp");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } else {
                Cookie cookie = new Cookie("user", user.getEmail());
                cookie.setMaxAge(60*60);
                response.addCookie(cookie);
                Cookie idCookie = new Cookie("user_id", String.valueOf(user.getId()));
                cookie.setMaxAge(60*60);
                response.addCookie(idCookie);
                HttpSession rolesession = request.getSession();
                rolesession.setAttribute("role",user.getRole());
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(60*60);

                response.sendRedirect(getServletContext().getContextPath());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
