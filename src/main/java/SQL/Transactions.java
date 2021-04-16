package SQL;

import JDBC.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class Transactions {
    public static Vector<String> temp = new Vector<>();
    public static void start(Vector<String> sqlQuery){
        temp = sqlQuery;
    }

    public static boolean end(){
        for (int i = 0; i < temp.size(); i++) {
            try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(temp.get(i))) {
                pstmt.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

}
