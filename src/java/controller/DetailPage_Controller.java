package controller;

import context.ContextPath;
import dal.NewsDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.News;

public class DetailPage_Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //check if the codes have any problem or not
        try {
            // creating variable needs
            String errReporter = null;
            ContextPath contextPath = new ContextPath();
            NewsDAO newsDAO = new NewsDAO();
            News detailNews = null;
            String imgPath = null;
            ArrayList<News> top5 = new ArrayList<>();
            top5 = newsDAO.get5NewsByMostRecents();
            News top1 = new News();
            top1 = newsDAO.getNewestNew();
            int id;
            //check if the detail id is existed or not
            try {
                //try to parse id to integer
                id = Integer.parseInt(request.getParameter("id"));
                //find the detail id in database
                detailNews = newsDAO.getNewByID(id);
                //if the id is not found return page not fond to errReporter
                if (detailNews == null) {
                    errReporter = "Page not found!";
                }else{
                    //if id found, get image path
                    imgPath = contextPath.getImage() + detailNews.getImage();
                }
            } catch (NumberFormatException ex) {
                errReporter = "Page not found !";
            }
            // send data to jsp file
            request.setAttribute("imgPath", imgPath);
            request.setAttribute("detailNews", detailNews);
            request.setAttribute("errReporter", errReporter);
            request.setAttribute("top1", top1);
            request.setAttribute("top5", top5);
            request.getRequestDispatcher("DetailPage.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
