package controller;

import dal.NewsDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.News;

public class SearchPage_Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Create variable for error reporting
            String errReporter = null;
            int curentPage = 0;
            NewsDAO newsDAO = new NewsDAO();
            ArrayList<News> SearchedList = new ArrayList<>();
            ArrayList<News> top5 = new ArrayList<>();
            News top1 = new News();
            
            // Get Searched text
            String searchedText = request.getParameter("searchedText").trim();
            if (searchedText.trim().length() == 0) {
                errReporter = "Page not found";
            }
            // Get current page position
            String pageIndex = request.getParameter("pageIndex");
            // Check if page index is existed or not
            if (pageIndex == null) {
                //if pageindex will be the first page
                pageIndex = "1";
            }
            // Count number of searched news by searchedText
            int totalNews = newsDAO.RecordCounter(searchedText);
            // Number of posts per page
            int pageSize = 6;
            // calculate Number of pages
            //check if check if totoal pageSize are divive by totalNews or not 
            //if yes, Num of page = the result else Num of page = the result + 1
            int NumOfPage = (totalNews % pageSize == 0) ? totalNews / pageSize : totalNews / pageSize + 1;
            //Check entered page number is fit or not
            try {
                curentPage = Integer.parseInt(pageIndex);
                if (curentPage > NumOfPage || curentPage < 1) {
                    errReporter = "Page not found";
                }
            } catch (NumberFormatException ex) {
                errReporter = "Page not found";
            }
            // get all of the seached records
            SearchedList = newsDAO.Searching(searchedText, curentPage, pageSize);
            top1 = newsDAO.getNewestNew();
            top5 = newsDAO.get5NewsByMostRecents();
            //send data to jsp file
            request.setAttribute("SearchedList", SearchedList);
            request.setAttribute("searchedText", searchedText);
            request.setAttribute("curentPage", curentPage);
            request.setAttribute("NumOfPage", NumOfPage);
            request.setAttribute("errReporter", errReporter);
            //request.setAttribute("listImagePath", listImagePath);
            request.setAttribute("top1", top1);
            request.setAttribute("top5", top5);
            request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
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
