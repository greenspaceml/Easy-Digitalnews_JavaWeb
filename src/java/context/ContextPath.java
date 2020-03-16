package context;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextPath {

    private InitialContext init;
    private Context cont;
    private String dbname, serverName, portNumber, image, username, password;
    public ContextPath() {
        try {
            init = new InitialContext();
            cont = (Context) init.lookup("java:comp/env");
            dbname = cont.lookup("dbName").toString();
            serverName = cont.lookup("serverName").toString();
            portNumber = cont.lookup("portNumber").toString();
            image = cont.lookup("image").toString();
            username = cont.lookup("username").toString();
            password = cont.lookup("password").toString();
        } catch (NamingException ex) {
            Logger.getLogger(ContextPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDbname() {
        return dbname;
    }

    public String getServerName() {
        return serverName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
