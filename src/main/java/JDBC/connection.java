package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static String url = "jdbc:postgresql://database-1.cmidvpydcvzn.us-east-2.rds.amazonaws.com:5432/postgres?user=postgres&password=Pos45066";
    private static String user = "sam";
    private static String password = "12345";

    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            //System.out.println("Connected to the PostgreSQL server successfully.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
