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
import java.util.Date;

@WebServlet(name = "SubmitRequestControllerServlet", value = "/SubmitRequestControllerServlet")
public class SubmitRequestControllerServlet extends HttpServlet {
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
        RequestPojo r = new RequestPojo();
        r.setTitle(req.getParameter("title"));
        r.setDescription(req.getParameter("description"));
        r.setRaisedBy(UserProfile.getUsername());
        r.setRequestDate(new Date().toString());
        r.setDepartment(req.getParameter("department"));
        r.setApprovalDate("pending");
        r.setApprovedBy("pending");
        r.setStatus("pending");
        try{
            boolean result = RequestModel.addRequest(r);
            System.out.println(result);
            if(result){
                System.out.println(result);
                resp.sendRedirect("RequestForm.html");
            }
            else{
                RequestDispatcher rd = req.getRequestDispatcher("RequestForm.html");
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
                        "    <strong>Request not submitted !</strong>\n" +
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
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}