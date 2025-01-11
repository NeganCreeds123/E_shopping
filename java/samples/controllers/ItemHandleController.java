/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controllers;

import java.io.File;

import java.io.IOException;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import samples.category.CategoryDAO;
import samples.category.CategoryDTO;
import samples.product.ProductDAO;
import samples.product.ProductDTO;
import sample.user.UserDTO;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ItemHandleController", urlPatterns = {"/ItemHandleController"})
public class ItemHandleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ItemHandleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ItemHandleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || !user.getRole().equals("AD")) {
                response.sendRedirect(request.getContextPath() + "/account/login.do");
                return;
            }
        String op = request.getParameter("op");
        switch (op) {
            case "add":
                addProduct(request, response);
                break;
            case "addCategory":
                addCategory(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "update":
                updateCategory(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "display":
                displayProduct(request, response);
                break;
//                 case "deleteCate":
//                deleteCate(request, response);
//                break;
//                case "displayCate":
//                displayCate(request, response);
//                break;
        }

    }

    protected void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ProductDAO productDAO = new ProductDAO();
        String itemName = request.getParameter("name");
        int Id = productDAO.makeID();
        int itemCategoryID = Integer.parseInt(request.getParameter("category"));
        double itemPrice = Double.parseDouble(request.getParameter("price"));
       String itemImage = itemName + ".jpg";

        String itemStatus = request.getParameter("status");

        if (productDAO.checkDuplicateName(itemName)) {
            request.setAttribute("status", "alert-danger");
            request.setAttribute("message", "Duplicate name");
            request.getRequestDispatcher("/item/add.do").forward(request, response);
        } else {
            // Create a ProductDTO object
            ProductDTO product = new ProductDTO();
            product.setId(Id);
            product.setName(itemName);
            product.setCategoryID(itemCategoryID);
            product.setPrice(itemPrice);

            product.setImage(itemImage);
            product.setStatus(itemStatus);

            // Call ProductDAO to create the product
            boolean check = productDAO.createProduct(product);
            if (check) {
                request.setAttribute("status", "alert-success");
                request.setAttribute("message", "Product create successfully.");

            } else {
                request.setAttribute("status", "alert-danger");
                request.setAttribute("message", "Product create failed.");
            }

        }

        request.getRequestDispatcher("/item/view.do").forward(request, response);

    }

    public void copyImage(String inputFilePath) throws IOException {
        //String inputFilePath = "D:\\HOC KY 5\\PRJ301\\Assignment_ShoppingManagement\\web\\image\\chrysanthemum.jpg"; // Replace with the actual path to the input file
        String destinationFolderPath = "C:\\Users\\Dell\\Desktop\\SWP391_SMK(offline)\\web\\images\\product";
        File inputFile = new File(inputFilePath);
        File destinationFolder = new File(destinationFolderPath);

        Path sourcePath = inputFile.toPath();
        Path destinationPath = new File(destinationFolder, inputFile.getName()).toPath();
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Image copied successfully!");

    }

    protected void addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Retrieve form data

        CategoryDAO cateDAO = new CategoryDAO();
        String name = request.getParameter("name");
        int Id = cateDAO.makeID();
        String status = request.getParameter("status");
        if (cateDAO.checkDuplicateName(name)) {
            request.setAttribute("status", "alert-danger");
            request.setAttribute("message", "Duplicate name");

        } else {
            CategoryDTO category = new CategoryDTO();
            category.setId(Id);
            category.setName(name);
            category.setStatus(status);

            // Call ProductDAO to create the product
            cateDAO.createCategory(category);
            boolean check = cateDAO.createCategory(category);
            if (check) {
                request.setAttribute("status", "alert-success");
                request.setAttribute("message", "category create successfully.");

            } else {
                request.setAttribute("status", "alert-danger");
                request.setAttribute("message", "category create failed.");
            }
        }

        request.getRequestDispatcher("/item/category.do").forward(request, response);

    }

    protected void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        // Retrieve form data

        ProductDAO productDAO = new ProductDAO();
        String itemName = request.getParameter("name");
        int Id = Integer.parseInt(request.getParameter("id"));
        int itemCategoryID = Integer.parseInt(request.getParameter("category"));
        double itemPrice = Double.parseDouble(request.getParameter("price"));

        String itemImage = itemName + ".jpg";
        String itemStatus = request.getParameter("status");

        // Create a ProductDTO object
        ProductDTO product = new ProductDTO();
        product.setId(Id);
        product.setName(itemName);
        product.setCategoryID(itemCategoryID);
        product.setPrice(itemPrice);

        product.setImage(itemImage);
        product.setStatus(itemStatus);
//        uploadImage(request, response);
        // Call ProductDAO to create the product
        boolean check = productDAO.updateProduct(product);
        if (check) {
            request.setAttribute("status", "alert-success");
            request.setAttribute("message", "Product update successfully.");
        } else {
            request.setAttribute("status", "alert-danger");
            request.setAttribute("message", "Product update failed..");
        }

        request.getRequestDispatcher("/item/view.do").forward(request, response);

    }


    protected void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Retrieve form data

        CategoryDAO dao = new CategoryDAO();

        int Id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String status = request.getParameter("status");

        CategoryDTO category = new CategoryDTO();
        category.setId(Id);
        category.setName(name);
        category.setStatus(status);

        // Call ProductDAO to create the product
        boolean check = dao.updateCategory(category);
        if (check) {
            request.setAttribute("status", "alert-success");
            request.setAttribute("message", "category update successfully.");

        } else {
            request.setAttribute("status", "alert-danger");
            request.setAttribute("message", "category update failed.");
        }

        request.getRequestDispatcher("/item/category.do").forward(request, response);  // Product created successfully
//            response.getWriter().println("category created successfully.");

    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int Id = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProduct(Id);

        request.getRequestDispatcher("/item/view.do").forward(request, response);

    }

    protected void displayProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int Id = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        productDAO.displayProduct(Id);

        request.getRequestDispatcher("/item/view.do").forward(request, response);

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
            Logger.getLogger(ItemHandleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ItemHandleController.class.getName()).log(Level.SEVERE, null, ex);
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
