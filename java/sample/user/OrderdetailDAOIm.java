/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author 84878
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderdetailDAOIm implements OrderdetailDAO {
    private static final String DB_URL = "jdbc:sqlserver://SMKiosk132.mssql.somee.com:1433;databaseName=SMKiosk132;user=nhuttran09_SQLLogin_4;password=xn6wm3en6u";

    public List<OrderdetailDTO> getAllOrderdetails() {
        List<OrderdetailDTO> orderdetails = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT [Id], [CustomerId], [Date], [Total] FROM [Orders]")) {
            while (rs.next()) {
                OrderdetailDTO orderdetail = extractOrderdetailFromResultSet(rs);
                orderdetails.add(orderdetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderdetails;
    }

    private OrderdetailDTO extractOrderdetailFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("Id");
        int customerId = rs.getInt("CustomerId");
        Date date = rs.getDate("Date");
        double total = rs.getDouble("Total");
        return new OrderdetailDTO(id, customerId, date, total);
    }

    @Override
    public List<OrderdetailDTO> getAllOrderDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
 