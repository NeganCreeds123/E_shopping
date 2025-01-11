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
public class OrderGraphDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String TOTAL_GROUP_BY_DATE = "SELECT Date, SUM([Total]) AS Total FROM Orders GROUP BY Date;";
    private static final String TOTAL_EARNING = "SELECT SUM([Total]) AS Total FROM Orders;";
    private static final String NUM_OF_ORDERS = "SELECT COUNT(Orders.Id) AS NumberOfOrders FROM Orders;";
    private static final String TOTAL_SALE_QUANTITY = "SELECT SUM(OrderDetail.quantity) as TotalQuantity FROM OrderDetail;";
    private static final String NUM_OF_CUSTOMERS = "SELECT COUNT(Customer.Id) AS NumberOfCustomers FROM Customer;";

    public List<OrderGraphDTO> totalGroupByDate() throws SQLException {
        List<OrderGraphDTO> orderByDate = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(TOTAL_GROUP_BY_DATE);
                rs = pstmt.executeQuery();
                while (rs.next()) {

                    Date date = rs.getDate("Date");
                    double total = rs.getDouble("Total");
                    OrderGraphDTO order = new OrderGraphDTO(date, total);
                    orderByDate.add(order);
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
        return orderByDate;
    }

    public double totalEarning() throws SQLException {
        double total = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(TOTAL_EARNING);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    total = rs.getDouble("Total");
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
        return total;
    }

    public int numOfOrders() throws SQLException {
        int num = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(NUM_OF_ORDERS);
                rs = pstmt.executeQuery();
                 if (rs.next()) {
                    num = rs.getInt("NumberOfOrders");
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
        return num;
    }

    public int itemSold() throws SQLException {
        int num = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(TOTAL_SALE_QUANTITY);
                rs = pstmt.executeQuery();
               
                if (rs.next()) {
                     num = rs.getInt("TotalQuantity");
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
        return num;
    }

    public int numOfCustomers() throws SQLException {
        int num = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(NUM_OF_CUSTOMERS);
                rs = pstmt.executeQuery();
                   if (rs.next()) {
                        num = rs.getInt("NumberOfCustomers");
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
        return num;
    }
}
