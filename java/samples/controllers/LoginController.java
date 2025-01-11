package samples.controllers;


import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import samples.customer.CustomerDAO;
import samples.customer.CustomerDTO;
import samples.order.OrderGraphDAO;
import samples.order.OrderGraphDTO;
import samples.order.PieChartDAO;
import samples.order.PieChartDTO;
import sample.user.UserDAO;
import sample.user.UserDTO;

@WebServlet(
        name = "LoginController",
        urlPatterns = {"/LoginController"}
)
public class LoginController extends HttpServlet {

    private static final String CS = "CS";
    private static final String AD = "AD";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(email, password);
            CustomerDAO cusDao = new CustomerDAO();
            if (loginUser == null) {
                request.setAttribute("message", "Email or password is incorrect");
                request.getRequestDispatcher("/account/login.do").forward(request, response);
            } else {
                //phan quyen cho nguoi dung 
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String roleID = loginUser.getRole();
                String userID=loginUser.getUserID();
                CustomerDTO cusInfo = cusDao.loadCusById(userID);
                session.setAttribute("USER_INFO", cusInfo);
                switch (roleID) {
                    //dan toi trang chu cua tung role
                   case AD:
                        //get data for ordergraph
                        OrderGraphDAO graphDao = new OrderGraphDAO();
                        List<OrderGraphDTO> graphValue = graphDao.totalGroupByDate();
                        String jsonGraphValue = new Gson().toJson(graphValue);
                        // Set the JSON graphValue as a request attribute
                        session.setAttribute("graphValue", jsonGraphValue);

                        //get data for pieChart
                        PieChartDAO pieChartDao = new PieChartDAO();
                        List<PieChartDTO> pieChartValue = pieChartDao.saleNumOfProds();
                        String jsonPieChartValue = new Gson().toJson(pieChartValue);
                        // Set the JSON pieChartValue as a request attribute
                        session.setAttribute("pieChartValue", jsonPieChartValue);

                        //get totalEarning
                        Double total = graphDao.totalEarning();
                        session.setAttribute("TOTAL", total);
                        
                        //get numOfOrders
                        int num = graphDao.numOfOrders();
                        session.setAttribute("NUM_OF_ORDERS", num);
                        
                        //get itemSale
                        int itemSale = graphDao.itemSold();
                        session.setAttribute("ITEMS_SOLD", itemSale);
                        
                        //get numOfCustomers
                        int numOfCustomers = graphDao.numOfCustomers();
                        session.setAttribute("NUM_OF_CUSTOMERS", numOfCustomers);

                        // request.getRequestDispatcher("/account/login.do").forward(request, response);
                        response.sendRedirect(request.getContextPath() + "/home/index.do");
                        break;

                    case CS:
                        response.sendRedirect(request.getContextPath() + "/user/user.do");
                        break;
                    default:
                        request.setAttribute("message", "Email or password is incorrect");
                        request.getRequestDispatcher("/account/login.do").forward(request, response);
                        break;

                }
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
