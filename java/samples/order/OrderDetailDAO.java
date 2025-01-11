/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import samples.product.ProductDTO;
import samples.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class OrderDetailDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String LOAD_ORDER_DETAIL = "select P.Id,P.[Image],P.[Name],P.Price,P.CategoryId,P.[Status],OD.quantity from OrderDetail OD, Product P where OD.productID=P.Id And OD.ordersID=?";

    public List<OrderDetailDTO> loadOrderDetail(int orderId) throws SQLException {

        List<OrderDetailDTO> detailList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(LOAD_ORDER_DETAIL);
                pstmt.setInt(1, orderId);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int proId = rs.getInt("Id");
                    int categoryId = rs.getInt("CategoryId");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String status = rs.getString("Status");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("quantity");
                    ProductDTO pro = new ProductDTO(proId, categoryId, name, image, status, price);
                    OrderDetailDTO detail = new OrderDetailDTO(orderId,proId,quantity,pro);
                    detailList.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return detailList;
    }
}
