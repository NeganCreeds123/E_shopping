/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.utils;


/**
 *
 * @author Dell
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

//  private static final String DB_SERVER = "SMKiosk132.mssql.somee.com";
//    private static final String DB_NAME = "SMKiosk132";
//    private static final String DB_USER_NAME = "nhuttran09_SQLLogin_4";
//    private static final String DB_PASSWORD = "xn6wm3en6u";

   private static final String CONNECTION_STRING = "jdbc:sqlserver://SQL8005.site4now.net;database=db_a9c1c8_smkiosk";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(CONNECTION_STRING,"db_a9c1c8_smkiosk_admin","NewDatabase123@");
    }
}
