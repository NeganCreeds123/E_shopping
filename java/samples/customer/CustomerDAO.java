/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.customer;

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
public class CustomerDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String LOAD_CUSTOMER_INFO = "Select CS.Id , CS.[Name],CS.PhoneNum,CS.UserId,A.Email from Customer CS, Account A Where CS.UserId=A.UserId And A.Role = 'CS' ";
    private static final String LOAD_CUSTOMER_INFO_BY_ID = "Select CS.Id , CS.[Name],CS.PhoneNum,CS.UserId,A.Email from Customer CS, Account A Where CS.UserId=A.UserId AND A.UserId=?";
     private static final String LOAD_CUSTOMER_INFO_BY_ID2 = "Select Id , [Name],PhoneNum,UserId from Customer Where UserId=?";
     private static final String CHECK_EXIST_CUS_ID = "SELECT COUNT(*) FROM Customer WHERE  Id = ?";
    private static final String REGISTER = "INSERT INTO Customer (Id,Name,PhoneNum,UserId) VALUES (?,?,?,?)";
    
        public List<CustomerDTO> loadCus() throws SQLException {

        List<CustomerDTO> cusList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(LOAD_CUSTOMER_INFO);

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("Id");
                  
                    String name = rs.getString("Name");
                    String phoneNum = rs.getString("PhoneNum");
                    String userId = rs.getString("UserId");
                    String email = rs.getString("Email");

                    CustomerDTO dto = new CustomerDTO(id, name, phoneNum, userId, email);
                    cusList.add(dto);
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
        return cusList;
    }
    public boolean checkDuplicateCusID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement checkStm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {
                checkStm = conn.prepareStatement(CHECK_EXIST_CUS_ID);
                checkStm.setString(1, id);

                rs = checkStm.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    check = true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (checkStm != null) {
                checkStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public String randomCusID() throws SQLException {

        int min = 1000; // Minimum value of range
        int max = 9999; // Maximum value of range
        String ID;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        ID = "CS" + random_int;
        while (checkDuplicateCusID(ID)) {
            randomCusID();
        }
        return ID;

    }
    public boolean createCus(CustomerDTO cus) throws SQLException {
      
        boolean check = false;
        Connection conn = null;
        PreparedStatement checkStm = null;

        PreparedStatement insertStm = null;
        ResultSet rs = null;
     
        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {

                insertStm = conn.prepareStatement(REGISTER);
                insertStm.setString(1, cus.getId());
                insertStm.setString(2, cus.getName());
                insertStm.setString(3, cus.getPhoneNum());
                insertStm.setString(4, cus.getUserId());

                int exc = insertStm.executeUpdate();
                if (exc > 0) {
                    check = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (insertStm != null) {
                insertStm.close();
            }
            if (checkStm != null) {
                checkStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public CustomerDTO loadCusById(String id) throws SQLException {

        CustomerDTO cus = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(LOAD_CUSTOMER_INFO_BY_ID2);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {

                    String Id = rs.getString("Id");
                    String name = rs.getString("Name");
                    String phone = rs.getString("PhoneNum");
                    String userId = rs.getString("UserId");
                  

                    cus = new CustomerDTO(Id, name, phone,userId);

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
        return cus;
    }
}
