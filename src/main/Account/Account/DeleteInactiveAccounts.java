package Account;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class DeleteInactiveAccounts extends JInternalFrame implements ActionListener {
    private JLabel username = new JLabel("User name");
    private JComboBox name = new JComboBox();
    private JButton delete = new JButton("Delete");

    public DeleteInactiveAccounts() {
        super("Delete user accounts", false, true, false, true);
        this.setBounds(350, 200, 300, 200);
        this.setDefaultCloseOperation(2);
        this.setLayout((LayoutManager)null);
        this.username.setBounds(30, 30, 100, 30);
        //this.cmbUName.setBounds(120, 30, 130, 25);
        this.delete.setBounds(100, 90, 100, 25);
        this.add(this.username);
        this.add(this.name);
        this.add(this.delete);
        this.delete.addActionListener(this);
        this.LoadUserames();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:student");
            Statement st = con.createStatement();

            try {
                if (e.getSource() == this.delete) {
                    String sql = "delete from UAD where Username='" + this.name.getSelectedItem().toString() + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog((Component)null, "Database successfully updated", "Success", 1);
                    this.LoadUserames();
                }
            } catch (Exception var6) {
                JOptionPane.showMessageDialog((Component)null, "Error,Unable to perform database operation", "Error", 0);
            }
        } catch (Exception var7) {
            JOptionPane.showMessageDialog((Component)null, "Error on database connection, Cannot perform database operation", "Error", 0);
        }

    }

    private void LoadUserames() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:student");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Username from UAD");
            this.name.removeAllItems();

            while(rs.next()) {
                String name = rs.getString(1);
                if (!name.equalsIgnoreCase("admin")) {
                    this.name.addItem(name);
                }
            }
        } catch (Exception var5) {
            JOptionPane.showMessageDialog((Component)null, "Error while loading users list, Closing window" + var5.toString());
            this.dispose();
        }

    }
}

