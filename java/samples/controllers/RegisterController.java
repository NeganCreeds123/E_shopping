/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.customer.CustomerDAO;
import samples.customer.CustomerDTO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Dell
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final String ERROR = "/account/register.do";
    private static final String SUCCESS = "/account/login.do";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        boolean check = true;
        UserDAO dao = new UserDAO();
        CustomerDAO cusDao = new CustomerDAO();
        String userID = dao.randomID();
        String cusId = cusDao.randomCusID();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = "Activate";
        String confirmPassword = request.getParameter("cfPassword");
        String role = "CS";
        String regex = "^(.+)@(.+)$";
        String phoneRegex = "^0[1-9]\\d{8}$";
        if (!phone.matches(phoneRegex)) {
            request.setAttribute("message", "Phone inccorect format");
            check = false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (userID == null || userID.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("message", "Please fill in all fields.");
            check = false;
        }
        if (userID.length() < 2 || userID.length() > 10) {
            request.setAttribute("message", "UserID must be in range[2,10]");
            check = false;
        }
        if (dao.checkEmail(email)) {
            request.setAttribute("message", "Duplicate email");
            check = false;
        }
        if (!matcher.matches()) {
            request.setAttribute("message", "Email inccorect format");
            check = false;
        }
        if (!password.equals(confirmPassword)) {

            request.setAttribute("message", "Passwords do not match.");
            check = false;
        }
        if (check) {

            UserDTO user = new UserDTO(userID, email, password, role, status);
            boolean checkRegis = dao.register(user);
            if (checkRegis) {
                CustomerDTO cus = new CustomerDTO(cusId, name, phone, userID);
                boolean checkCus = cusDao.createCus(cus);
                if (checkCus) {
                    request.setAttribute("message", "Account created successfully. Please log in.");
                    url = SUCCESS;
                } else {
                    
                    url = ERROR;
                }

            } else {
                url = ERROR;
            }
        } else {
            url = ERROR;
        }

        request.getRequestDispatcher(url).forward(request, response);

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
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
