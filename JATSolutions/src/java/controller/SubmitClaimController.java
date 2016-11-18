/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Claim;
import model.DBController;

/**
 *
 * @author ja2-day
 */
public class SubmitClaimController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message;
        DBController jdbc = new DBController((Connection) request.getServletContext().getAttribute("connection"));
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        HttpSession session;
        session = request.getSession();

        if (jdbc.claimLimit((String) session.getAttribute("userName")) == false) {
            
            Claim newClaim = new Claim();
            newClaim.setMemID((String) (session.getAttribute("userName")));
            newClaim.setRationale(request.getParameter("claimRationale"));
            newClaim.setAmount(Double.parseDouble(request.getParameter("claimAmount")));
            jdbc.submitClaim(newClaim);
            message = "Claim submitted at " + currentDate;

            request.setAttribute("message", message);
            request.getRequestDispatcher("/docs/submitClaim").forward(request, response);

        } else {
            message = "Claim limit reached for the current year";

            request.setAttribute("message", message);
            request.getRequestDispatcher("/docs/submitClaim").forward(request, response);
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
