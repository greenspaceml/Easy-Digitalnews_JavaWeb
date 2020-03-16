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

//close the connection
    public void closeConnection(Connection con) throws Exception {
        if (!con.isClosed() && con != null) {
            //close the result set
            con.close();
        }
    }

//close the PreparedStatement
    public void closePreparedStatement(PreparedStatement ps) throws Exception {
        if (ps != null) {
            //close the result set
            ps.close();
        }
    }

//close the ResultSet
    public void closeResultSet(ResultSet rs) throws Exception {
        //check if the result set is closed or not
        if (rs != null) {
            //close the result set
            rs.close();
        }
    }

    public void closeAll(Connection con, PreparedStatement ps, ResultSet rs) throws Exception {
        closePreparedStatement(ps);
        closeResultSet(rs);
        closeConnection(con);
    }
}
