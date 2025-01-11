/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import samples.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class PieChartDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String SALE_FIGURE = "SELECT Product.[Name], SUM(OrderDetail.quantity) as Quantity FROM OrderDetail \n"
            + "INNER JOIN Product ON OrderDetail.productID = Product.Id\n"
            + "GROUP BY OrderDetail.productID, Product.[Name]";

    public List<PieChartDTO> saleNumOfProds() throws SQLException {
        List<PieChartDTO> saleFig = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(SALE_FIGURE);
                rs = pstmt.executeQuery();
                while (rs.next()) {

                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    PieChartDTO data = new PieChartDTO(name, quantity);
                    saleFig.add(data);
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
        return saleFig;
    }
}
