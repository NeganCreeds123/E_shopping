/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import samples.order.OrderDAO;
import samples.order.OrderDTO;
import samples.order.OrderDetailDAO;
import samples.order.OrderDetailDTO;
import sample.user.UserDTO;

/**
 *
 * @author Dell
 */
@WebServlet(name = "OrderController", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
         HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || !user.getRole().equals("AD")) {
                response.sendRedirect(request.getContextPath() + "/account/login.do");
                return;
            }
        switch (action) {

            case "history":
      
                    OrderDAO dao = new OrderDAO();

                    List<OrderDTO> orderList = dao.readOrder(); // Handle the exception appropriately, e.g., display an error page
                    request.setAttribute("orders", orderList);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
           
                break;
  case "detail":
           
                   int orderId = Integer.parseInt(request.getParameter("orderId"));
                     OrderDetailDAO ODdao = new OrderDetailDAO();

                    List<OrderDetailDTO> detailList = ODdao.loadOrderDetail(orderId); // Handle the exception appropriately, e.g., display an error page
                    request.setAttribute("detail", detailList);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
              
                break;
            default:
            //Show error page
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
