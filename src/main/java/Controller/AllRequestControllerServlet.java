package Controller;

import Model.RequestModel;
import pojo.RequestPojo;
import util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AllRequestControllerServlet", value = "/AllRequestControllerServlet")
public class AllRequestControllerServlet extends HttpServlet {
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
        try{
            List<RequestPojo> rList = RequestModel.getAllRequestById(UserProfile.getUsername());
            if(rList.isEmpty()){
                RequestDispatcher rd = req.getRequestDispatcher("ShowAllRequest.jsp");
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
                        "    <strong>No record found !</strong>\n" +
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
            else{
                req.setAttribute("rList", rList);
                RequestDispatcher rd = req.getRequestDispatcher("ShowAllRequest.jsp");
                rd.forward(req, resp);
            }
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}