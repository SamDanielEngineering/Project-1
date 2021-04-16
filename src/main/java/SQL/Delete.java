package SQL;

import JDBC.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static boolean dropTable(String tableName){
        String SQL = "DROP TABLE " + tableName + ";";

        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean dropRow(String tableName, String columnName, String rowValue){
        String SQL = "DELETE FROM " + tableName + " ";
        SQL += "WHERE " + columnName + " = '" + rowValue + "';";


        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean dropColumn(String tableName, String columnName){
        String SQL = "ALTER TABLE " + tableName + " ";
        SQL += "DROP COLUMN " + columnName + ";";


        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
