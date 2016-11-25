/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBController;
import model.Member;

/**
 *
 * @author jm2-wright
 */
public class RegistrationController extends HttpServlet {

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

        String message,
                date,
                password,
                name,
                initialString,
                userName;
        char[] initial;
        String[] surname;
        DBController jdbc;
        
        jdbc = new DBController((Connection) request.getServletContext().getAttribute("connection"));
        date = request.getParameter("dob").replaceAll("(..)-(..)-(....)", "$3-$2-$1");
        password = request.getParameter("dob").replaceAll("(..)-(..)-(..)(..)", "$1$2$4");
        name = request.getParameter("name");
        initial = new char[1];
        initial[0] = name.charAt(0);
        initialString = new String(initial);
        surname = name.split(" ");
        userName = initialString + "-" + surname[1];
        userName = userName.toLowerCase();
        
        
        if (jdbc.idExist(userName) == false) {
            Member newMember = new Member();
            newMember.setId(userName);
            newMember.setName(request.getParameter("name"));
            newMember.setAddress(request.getParameter("address"));
            newMember.setDob(Date.valueOf(date));

            jdbc.registerMember(newMember, password);
            message = "user: " + userName + " added";
        } else {
            message = "user: " + userName + " already taken!";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/docs/register").forward(request, response);

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
