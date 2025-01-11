/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import samples.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class CategoryDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String LOAD_CATEGORY = "SELECT * FROM Category ";
    private static final String UPDATE = "UPDATE Category SET Name = ?, Status = ? WHERE Id = ?";
private static final String CREATE = "INSERT INTO Category (Id, Name, Status) VALUES (?, ?, ?)";
private static final String COUNT = " SELECT COUNT(*) FROM Category";
 private static final String CHECK_EXIST_NAME = "SELECT COUNT(*) FROM Category where  Name = ?";
  public boolean checkDuplicateName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement checkStm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
//            Xu ly o day
            conn = DBUtils.getConnection();
            if (conn != null) {
                checkStm = conn.prepareStatement(CHECK_EXIST_NAME);
                checkStm.setString(1, name);

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
    public int countCategory() throws SQLException {

        Statement stm = null;

        int count = 0;
        try {
//            Xu ly o day
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.createStatement();
                rs = stm.executeQuery(COUNT);
                rs.next();
                count = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;
    }
    public List<CategoryDTO> readCategory() throws SQLException {
        List<CategoryDTO> cateList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement(LOAD_CATEGORY);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String status = rs.getString("Status");
                    CategoryDTO dto = new CategoryDTO(id, name, status);
                    cateList.add(dto);
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
        return cateList;
    }
 public int makeID() throws SQLException {

        int id = 10001 + countCategory();
        return id;

    }

    public boolean createCategory(CategoryDTO cate) throws SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(CREATE);
                pstmt.setInt(1, cate.getId());
               pstmt.setString(2, cate.getName());
             pstmt.setString(3, cate.getStatus());
              int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    check =true;
                } 
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    public boolean updateCategory(CategoryDTO cate) throws SQLException {
         boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(UPDATE);

                pstmt.setString(1, cate.getName());

                pstmt.setString(2, cate.getStatus());
                pstmt.setInt(3, cate.getId());
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    check =true;
                } 
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

}
