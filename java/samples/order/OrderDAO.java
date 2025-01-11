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
 * @author Dell
 */
public class OrderDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String LOAD_ORDER = "SELECT O.Id,O.CustomerId,O.[Date],O.Total,C.[Name] FROM [Orders] O,Customer C Where O.CustomerId=C.Id";

    public List<OrderDTO> readOrder() throws SQLException {

        List<OrderDTO> orderList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(LOAD_ORDER);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String CusId = rs.getString("CustomerId");
                    Date date = rs.getDate("Date");
                    double total = rs.getDouble("Total");
String name = rs.getString("Name");
                    OrderDTO order = new OrderDTO(id, CusId, date, total,name);
                    orderList.add(order);
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
        return orderList;
    }

    
}
