package Account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;

public class Settings {
    public Settings() {
    }

    public static Dimension getScreenSize() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return d;
    }

    public static Connection getDBConnection() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:student");
            return con;
        } catch (Exception var2) {
            return null;
        }
    }
}

