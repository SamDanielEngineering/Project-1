package SQL;

import JDBC.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static boolean updateTableName(String tableName, String newValue){
        String SQL = "ALTER TABLE " + tableName + " " +
                "RENAME TO " + newValue + ";";

        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateRowValue(String tableName, String columnName, String columnValue, String newRowValue){
        String SQL = "UPDATE " + tableName + " ";
        SQL += "SET " + columnName + " = '" + newRowValue + "' ";
        SQL += "WHERE " + columnName + " = '" + columnValue + "';";


        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateColumn(String tableName, String columnName, String newName){
        String SQL = "ALTER TABLE " + tableName + " ";
        SQL += "RENAME COLUMN " + columnName + " TO " + newName + ";";


        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }    }

}
