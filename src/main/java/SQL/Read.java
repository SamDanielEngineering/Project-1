package SQL;

import Cache.CacheHelper;
import JDBC.connection;

import java.sql.*;

public class Read {

    public static boolean doesTheCacheContainTheTableThenDelete(String tableName, CacheHelper cache) {
        if (cache.myCache.containsKey((tableName.hashCode()))) {
            sqlTable temp = cache.myCache.get(tableName.hashCode());
            if (temp.tableName.equals(tableName)) {
                cache.myCache.remove(tableName.hashCode());
                return true;
            }
        } else
            return false;
        return false;
    }

    public static sqlTable getTable(String tableName, CacheHelper cache) {
        if (cache.myCache.containsKey((tableName.hashCode()))) {
            sqlTable temp = cache.myCache.get(tableName.hashCode());
            if (temp.tableName.equals(tableName)) {
                return temp;
            }
        } else {
            String SQL = "SELECT * " +
                    "FROM " + tableName + ";";

            try (Connection conn = connection.connect(); Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                sqlTable temp2 = new sqlTable();
                ResultSet rs = stmnt.executeQuery(SQL);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    temp2.columnNames.add(rsmd.getColumnName(i + 1));
                }
                rs.first();
                while (rs.next()) {
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        temp2.rowEntry.add(rs.getString(i + 1));
                    }
                    temp2.rows.add(temp2.rowEntry);
                    temp2.rowEntry.clear();
                }
                temp2.setTableName(tableName);
                cache.myCache.put(tableName.hashCode(), temp2);
                return cache.myCache.get(tableName.hashCode());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        return null;
    }

}
