/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import samples.utils.DBUtils;

/**
 *
 * @author 84878
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import samples.order.OrderDTO;
import samples.order.OrderDetailDTO;

public class ProductDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String LOAD_PRODUCT = "SELECT * FROM Product ";
    private static final String LOAD_PRODUCT_BY_ID = "SELECT * FROM Product WHERE id=?";
    private static final String CHANGE_STATUS_DELETE = "UPDATE Product SET Status = 'Delete' WHERE Id = ?";
    private static final String CHANGE_STATUS_DISPLAY = "UPDATE Product SET Status = 'Display' WHERE Id = ?";
    private static final String UPDATE = "UPDATE Product SET CategoryId = ?, Name = ?, Image = ?, Status = ?, Price = ? WHERE Id = ?";
    private static final String CREATE = "INSERT INTO Product (Id,CategoryId, Name, Image, Status, Price) VALUES (?,?, ?, ?, ?, ?)";
    private static final String COUNT = " SELECT COUNT(*) FROM Product";
    private static final String ADD_ORDER = "INSERT INTO Orders (Id,CustomerId,date,total) values(?,?,?,?)";
 private static final String CHECK_EXIST_NAME = "SELECT COUNT(*) FROM Product where  Name = ?";
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
    public List<ProductDTO> readProduct() throws SQLException {

        List<ProductDTO> productList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(LOAD_PRODUCT);

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("Id");
                    int categoryId = rs.getInt("CategoryId");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String status = rs.getString("Status");
                    double price = rs.getDouble("Price");

                    ProductDTO dto = new ProductDTO(id, categoryId, name, image, status, price);
                    productList.add(dto);
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
        return productList;
    }

    public ProductDTO readProductById(int id) throws SQLException {

        ProductDTO pro = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(LOAD_PRODUCT_BY_ID);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {

                    int categoryId = rs.getInt("CategoryId");
                    String name = rs.getString("Name");
                    String image = rs.getString("Image");
                    String status = rs.getString("Status");
                    double price = rs.getDouble("Price");

                    pro = new ProductDTO(id, categoryId, name, image, status, price);

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
        return pro;
    } 
    public List<ProductDTO> searchByName(String searchValue) throws SQLException {
    List<ProductDTO> productList = new ArrayList<>();
    try {
        con = DBUtils.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM Product WHERE Name LIKE ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchValue + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                int categoryId = rs.getInt("CategoryId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String status = rs.getString("Status");
                double price = rs.getDouble("Price");

                ProductDTO dto = new ProductDTO(id, categoryId, name, image, status, price);
                productList.add(dto);
            }
        }
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
    return productList;
}
    public int countProduct() throws SQLException {

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

    public int makeID() throws SQLException {

        int id = 1001 + countProduct();
        return id;

    }

    public boolean createProduct(ProductDTO product) throws SQLException {
          boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(CREATE);
                pstmt.setInt(1, product.getId());
                pstmt.setInt(2, product.getCategoryID());
                pstmt.setString(3, product.getName());
                pstmt.setString(4, product.getImage());
                pstmt.setString(5, product.getStatus());
                pstmt.setDouble(6, product.getPrice());

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

    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(UPDATE);
                pstmt.setInt(1, product.getCategoryID());
                pstmt.setString(2, product.getName());
                pstmt.setString(3, product.getImage());
                pstmt.setString(4, product.getStatus());
                pstmt.setDouble(5, product.getPrice());
                pstmt.setInt(6, product.getId());

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

    public boolean deleteProduct(int productId) throws SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(CHANGE_STATUS_DELETE);
                pstmt.setInt(1, productId);

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

    public boolean displayProduct(int Id) throws SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

                pstmt = con.prepareStatement(CHANGE_STATUS_DISPLAY);
                pstmt.setInt(1, Id);

            
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
//
//    public int addOrder(OrderDTO dto) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                
//                stm = conn.prepareStatement(ADD_ORDER);
//                stm.setInt(1,dto.getId());
//                stm.setString(2, dto.getCusId());
//                stm.setDate(3, dto.getDate());
//                stm.setDouble(4, dto.getTotal());
//                return stm.executeUpdate();
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return -1;
//    }
//
//    public int addDetail(OrderDetailDTO dto) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = "insert into OrderDetail (ordersId,productId,quantity) values(?,?,?)";
//                stm = conn.prepareStatement(sql);
//                stm.setInt(1,dto.getOrderId());
//                stm.setInt(2, dto.getProductId());
//                stm.setInt(3, dto.getQuantity());
//                return stm.executeUpdate();
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return -1;
//    }
//    
    public void copyImage(String imgName,String src) throws FileNotFoundException, IOException{
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(src));
            os = new FileOutputStream(new File("path/to/img/dest"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
