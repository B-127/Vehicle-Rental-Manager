package GUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class GUIdbConnector {
    public static Connection getDbConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/oopcwdb";         //oopcwdb is the name of the vehicle list database.
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch(Exception e){System.out.println(e);}
        return null;
    }

}
