package Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Register extends JInternalFrame implements ActionListener {

    private JRadioButton FitnessEnthusiast, Admin, Trainer;
    private JLabel lblUsername, lblPassword, lblCPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword, txtCPassword;
    private JButton btnSave;
    private ButtonGroup group;

    public Register() {
        super("User registration", false, true, false, true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(300, 175);
        this.setSize(350, 300);
        group = new ButtonGroup();
        FitnessEnthusiast = new JRadioButton("Fitness Enthusiast");
        Admin = new JRadioButton("Administrator");
        Trainer = new JRadioButton("Trainer");
        lblUsername = new JLabel("User name");
        lblPassword = new JLabel("Enter the password");
        lblCPassword = new JLabel("Confirm the password");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtCPassword = new JPasswordField();
        btnSave = new JButton("Save");
        lblUsername.setBounds(30, 30, 100, 30);
        lblPassword.setBounds(30, 80, 150, 30);
        lblCPassword.setBounds(30, 130, 150, 30);
        txtUsername.setBounds(150, 30, 150, 25);
        txtPassword.setBounds(150, 80, 150, 25);
        txtCPassword.setBounds(150, 130, 150, 25);
        FitnessEnthusiast.setBounds(170, 170, 150, 30);
        Admin.setBounds(170, 170, 150, 30);
        Trainer.setBounds(170, 170, 150, 30);
        btnSave.setBounds(120, 210, 100, 25);
        group.add(FitnessEnthusiast);
        group.add(Admin);
        group.add(Trainer);
        add(lblUsername);
        add(lblPassword);
        add(lblCPassword);
        add(txtUsername);
        add(txtPassword);
        add(txtCPassword);
        add(FitnessEnthusiast);
        add(Admin);
        add(Trainer);
        btnSave.addActionListener(this);
        FitnessEnthusiast.setSelected(true);
    }

    public void actionPerformed(ActionEvent e) {
        String type = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:user");
            Statement st = con.createStatement();
            try {
                if (e.getSource() == btnSave) {
                    String Username = txtUsername.getText();
                    char[] Password = txtPassword.getPassword();
                    char[] CPassword = txtCPassword.getPassword();
                    if (!CPassword.equals(Password)) {
                        JOptionPane.showMessageDialog(null, "Password and Confirm Password fields do not match", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String str = "insert into (Username, Password, Type) values(?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(str);
                    if (FitnessEnthusiast.isSelected()) {
                        type = "FitnessEnthusiast";
                    } else if (Admin.isSelected()) {
                        type = "Admin";
                    } else if (Trainer.isSelected()) {
                        type = "Trainer";
                    }
                    String pass = new String(Password);
                    String cpass = new String(CPassword);
                    ps.setString(1, Username);
                    ps.setString(2, pass);
                    ps.setString(3, type);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Database successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtCPassword.setText("");
                }
            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "Error, unable to perform database operation", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error on database connection, Cannot perform database operation", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
