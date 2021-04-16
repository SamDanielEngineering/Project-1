package JDBC;

import Threads.SimpleThreadFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFactory {
    private String url = "";
    private String user = "";
    private String password = "";
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    public static ConnectionFactory conn = null;


    public ConnectionFactory() throws SQLException {
        try(Connection connect = DriverManager.getConnection(this.url)){
            conn = create(this.url, this.user, this.password);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }



    public ConnectionFactory(String url, String user, String password, List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = pool;
    }

    public ConnectionFactory create(String url, String user, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionFactory(url, user, password, pool);
    }



    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }


    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

}
