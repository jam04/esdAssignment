/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.DBController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author James
 */
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        String id = request.getRequestURI().substring(request.getContextPath().length());
        String include;
        DBController jdbc = new DBController((Connection) request.getServletContext().getAttribute("connection"));
        String message = "";
        HttpSession session;

        switch (id) {
            case "/docs/login":
                include = "login.jsp";
                break;
            case "/docs/home":
                session = request.getSession();
                if (session.getAttribute("userName") == null) {//not signed in
                    if (jdbc.checkAdmin(request.getParameter("username"))) {//is an admin
                        session.setAttribute("userName", request.getParameter("username"));
                        include = "adminHome.jsp";
                    } else if (jdbc.validateLogin(request.getParameter("username"), request.getParameter("password"))) { //is a normal user
                        session.setAttribute("userName", request.getParameter("username"));
                        include = "userHome.jsp";
                    } else {
                        message = "Incorect username or password";
                        include = "login.jsp";
                        session.invalidate();
                        request.setAttribute("message", message);
                    }

                } else if (jdbc.checkAdmin((String) (session.getAttribute("userName")))) {//signed in and admin
                    include = "adminHome.jsp";
                } else {//signed in and user
                    include = "userHome.jsp";
                }
                break;
            case "/docs/register":
                include = "register.jsp";
                break;
            case "/docs/submitClaim":
                include = "submitClaim.jsp";
                break;
            case "/docs/submitPayment":
                include = "submitPayment.jsp";
                break;
            case "/docs/listApplications":
                include = "listApplications.jsp";
                break;
            case "/docs/report":
                include = "report.jsp";
                break;
            case "/docs/listBalance":
                include = "listBalance.jsp";
                break;
            case "/docs/listClaims":
                include = "listClaims.jsp";
                break;
            case "/docs/signOut":
                session = request.getSession();
                session.invalidate();
                include = "login.jsp";
                message = "Sign out sucessful";
                request.setAttribute("message", message);
                break;
            case "/docs/sendClaimFee":
                jdbc.claimFee();
                include = "adminHome.jsp";
                message = "Claim fee sent to members";
                request.setAttribute("message", message);
                break;
            default:
                include = "Error.jsp";
        }
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        String weatherReport = "";
        try {
            db = dbf.newDocumentBuilder();
            Document doc;
            doc = db.parse(new URL("http://www.myweather2.com/developer/forecast.ashx?uac=j9F1QFk75Z&output=xml&query=SW1").openStream());
            
            weatherReport += doc.getElementsByTagName("curren_weather").item(0).getChildNodes().item(5).getTextContent() + ",";
            weatherReport += doc.getElementsByTagName("forecast").item(0).getChildNodes().item(4).getChildNodes().item(0).getTextContent() + ",";
            weatherReport += doc.getElementsByTagName("forecast").item(1).getChildNodes().item(4).getChildNodes().item(0).getTextContent();
            
            request.setAttribute("weatherReport", weatherReport);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute(
                "doco", include);
        request.getRequestDispatcher(
                "/WEB-INF/docs/main.jsp").forward(request, response);

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
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrontController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrontController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
