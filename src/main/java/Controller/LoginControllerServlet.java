package Controller;

import Model.UserModel;
import pojo.UserPojo;
import util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginControllerServlet", value = "/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if(req.getParameter("username").isEmpty() || req.getParameter("password").isEmpty() || req.getParameter("department").isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <title>No User Found</title>\n" +
                    "  <!-- Bootstrap CSS -->\n" +
                    "  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"container mt-5\">\n" +
                    "  <div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                    "    <strong>Please input all the fields !</strong>\n" +
                    "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "      <span aria-hidden=\"true\">&times;</span>\n" +
                    "    </button>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<!-- Bootstrap JS -->\n" +
                    "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>\n" +
                    "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n");
            rd.include(req, resp);
        }

        UserPojo user = new UserPojo();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setDepartment(req.getParameter("department"));

        try{
            String username = UserModel.isUserValid(user);
            RequestDispatcher dispatcher ;
            if (username == null) {
                dispatcher = req.getRequestDispatcher("index.html");
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "  <title>No User Found</title>\n" +
                        "  <!-- Bootstrap CSS -->\n" +
                        "  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<div class=\"container mt-5\">\n" +
                        "  <div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                        "    <strong>No User Found!</strong> Please try again.\n" +
                        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "      <span aria-hidden=\"true\">&times;</span>\n" +
                        "    </button>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "<!-- Bootstrap JS -->\n" +
                        "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\n" +
                        "<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>\n" +
                        "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n");
                dispatcher.include(req, resp);
            }
            else{
                UserProfile.setUsername(username);
//                System.out.println("HI");
//                System.out.println(req.getParameter("department"));
                if(req.getParameter("department").equalsIgnoreCase("legal")){
//                    System.out.println(req.getParameter("department"));
                    dispatcher = req.getRequestDispatcher("legalDashboard.html");
                    dispatcher.forward(req, resp);
                } else if (req.getParameter("department").equalsIgnoreCase("Finance")) {
                    dispatcher = req.getRequestDispatcher("financeDashboard.html");
                    dispatcher.forward(req, resp);
                } else if (req.getParameter("department").equalsIgnoreCase("Management")) {
                    dispatcher = req.getRequestDispatcher("managementDashboard.html");
                    dispatcher.forward(req, resp);
                }
            }
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}