package Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    private JButton login;
    private JButton cancel;
    private JLabel username;
    private JLabel password;
    private JTextField txtUsername;
    private JTextField txtPassword;

    public Login()
    {
        super("Login");
        this.setSize(350, 200);
        this.setLayout((LayoutManager)null);
        this.setResizable(false);
        //this.setLocation(Settings.getScreenSize().width / 2 - 175, Settings.getScreenSize().height / 2 - 150);
        this.setDefaultCloseOperation(3);
        this.username = new JLabel("Username");
        this.password = new JLabel("Password");
        this.txtUsername = new JTextField();
        this.txtPassword = new JPasswordField();
        this.login = new JButton("Login", new ImageIcon(ClassLoader.getSystemResource("Images/login.png")));
        this.cancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("Images/cancel.png")));
        this.username.setBounds(50, 40, 140, 25);
        this.txtUsername.setBounds(150, 40, 130, 25);
        this.password.setBounds(50, 80, 140, 25);
        this.txtPassword.setBounds(150, 80, 130, 25);
        this.login.setBounds(50, 120, 100, 25);
        this.cancel.setBounds(180, 120, 100, 25);
        this.login.addActionListener(this);
        this.cancel.addActionListener(this);
        this.add(this.username);
        this.add(this.password);
        this.add(this.txtUsername);
        this.add(this.txtPassword);
        this.add(this.login);
        this.add(this.cancel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.login) {
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con = DriverManager.getConnection("jdbc:odbc:student");

                try {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM UAD WHERE Username='" + this.txtUsername.getText() + "' and Password='" + this.txtPassword.getText() + "'");
                    if (rs.next()) {
                        if (rs.getString(3).equals("Student")) {
                            User user = new User();
                            user.setVisible(true);
                        }

                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "Invalid username or password", "Invalid", 0);
                    }

                    con.close();
                } catch (Exception var6) {
                    JOptionPane.showMessageDialog((Component)null, "Invalid username or password", "Invalid", 0);
                    this.txtUsername.setText("");
                    this.txtPassword.setText("");
                }
            } catch (Exception var7) {
                JOptionPane.showMessageDialog((Component)null, "Unable to connect to the database", "Connection error", 0);
            }
        }

        if (e.getSource() == this.cancel) {
            System.exit(0);
        }

    }
}

