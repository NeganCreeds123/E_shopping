/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import samples.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private static final String LOGIN = "SELECT UserId, [password], Email, [Role] FROM Account WHERE Email = ? AND [password] = ? AND Status = 'Activate'";
    private static final String REGISTER = "INSERT INTO Account (UserId,Email,[password],[Role],Status) VALUES (?,?,?,?,?)";
    private static final String CHECK_EXIST = "SELECT COUNT(*) FROM Account where Email = ? ";
    private static final String CHECK_EXIST_ID = "SELECT COUNT(*) FROM Account where  UserId = ?";

    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String UserId = rs.getString("UserId");
                    password = "***";
                    String role = rs.getString("Role");
                    user = new UserDTO(UserId, email, password, role);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean checkDuplicateID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement checkStm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {
                checkStm = conn.prepareStatement(CHECK_EXIST_ID);
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

    public boolean checkEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement checkStm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                checkStm = conn.prepareStatement(CHECK_EXIST);
                checkStm.setString(1, email);

                rs = checkStm.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    // Account name already exists, throw an exception with a message
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

    public boolean register(UserDTO user) throws SQLException {

        boolean check = false;
        Connection conn = null;

        PreparedStatement insertStm = null;
        ResultSet rs = null;

        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {

                insertStm = conn.prepareStatement(REGISTER);
                insertStm.setString(1, user.getUserID());
                insertStm.setString(2, user.getEmail());
                insertStm.setString(3, user.getPassword());
                insertStm.setString(4, user.getRole());
                insertStm.setString(5, user.getStatus());
                int rowsAffected = insertStm.executeUpdate();
                if (rowsAffected > 0) {
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

            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String randomID() throws SQLException {

        int min = 1000; // Minimum value of range
        int max = 9999; // Maximum value of range
        String ID;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        ID = "SMK" + random_int;
        while (checkDuplicateID(ID)) {
            randomID();
        }
        return ID;

    }

    

}
