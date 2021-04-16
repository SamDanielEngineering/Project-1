package SQL;

import JDBC.ConnectionFactory;

import java.sql.Connection;
import JDBC.connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class Create {
    public static boolean createTable(String tableName){
        String SQL = "CREATE TABLE " + tableName + "(" +
                "test1 VARCHAR( 50 )" +
                ");";
        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            //pstmt.setString(1, tableName);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static <T> boolean createRow(String tableName, Vector<String> columnNames, Vector<T> row){
        String SQL = "INSERT INTO " + tableName + "(";
        int i;
        for (i = 0; i < columnNames.size() - 1; i++){
            SQL += columnNames.get(i) + ", ";
        }
        SQL += columnNames.get(i) + ") VALUES('";
        for (i = 0; i < row.size() - 1; i++){
            SQL += row.get(i) + "', ";
        }
        SQL += row.get(i) + "');";


        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean createColumn(String tableName, String columnName, String columnType){
        String SQL = "ALTER TABLE " + tableName + " ADD COLUMN ";
        SQL += columnName + " ";
        SQL += columnType + ";";

        try (Connection conn = connection.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
