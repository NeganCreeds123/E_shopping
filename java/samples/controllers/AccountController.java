/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        String url = "/WEB-INF/layouts/main.jsp";
        //xu ly yeu cau
        switch (action) {

            case "login":
                //Hien form login
                request.getRequestDispatcher(url).forward(request, response);
                break;
                case "register":
                //Hien form register
                request.getRequestDispatcher(url).forward(request, response);
                break;
                  case "forget-pass":
                //Hien form forget-pass
                request.getRequestDispatcher(url).forward(request, response);
                break;
//            case "login_handler":
//                //Xu ly login form
//                login_handler(request, response);
//                //  request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
//                break;
//            case "logout":
//                //Viet code xu ly neu can
//                logout(request, response);
//
//                //request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
//                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "error");
                request.setAttribute("message", "Invalid request.");
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/home/index.do");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
