package samples.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GenerateQRController", urlPatterns = {"/GenerateQRController"})
public class GenerateQRController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userId = request.getParameter("userId");
            String qrCodeUrl = generateQR(userId);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("QR_URL", qrCodeUrl);

        } catch (IOException e) {
            response.getWriter().println("Failed to load qr: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/home/user.do").forward(request, response);

    }

    private String generateQR(String userId) throws IOException {
        String qrCodeUrl = "";

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitMatrix matrix = new QRCodeWriter().encode(userId, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToStream(matrix, "PNG", baos);

            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            qrCodeUrl = "data:image/png;base64," + base64Image;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return qrCodeUrl;
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
