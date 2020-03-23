package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContext {

    private String url;
    private String username;
    private String password;

    public DBContext() {
        ContextPath contextPath = new ContextPath();
        url = "jdbc:sqlserver://" + contextPath.getServerName() + ":" + contextPath.getPortNumber() + ";databaseName=" + contextPath.getDbname();
        username = contextPath.getUsername();
        password = contextPath.getPassword();
    }

//get the connection by database's url,name and password
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //return connection to called functions
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        //check if the result set is closed or not
        if (rs != null && !rs.isClosed()) {
            //close the result set
            rs.close();
        }
        if (ps != null && !ps.isClosed()) { 
            //close the preparestement
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
