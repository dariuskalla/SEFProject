package Account;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EditTraining extends JInternalFrame implements ActionListener {

    private JLabel lblName,  lblDescription;
    private JTextField txtName,  txtDescription;
    private JButton btnSave,  btnCancel;

    public EditTraining() {
        super("Add Training", false, true, false, true);
        setSize(200, 200);
        setLocation(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        getContentPane().setLayout(new GridLayout(7, 2));

        lblName = new JLabel("Name");
        lblDescription = new JLabel("Description");
        txtName = new JTextField();
        txtDescription = new JTextField();
        btnSave = new JButton("Update");
        btnCancel = new JButton("Cancel");

        c.add(lblName);
        c.add(lblDescription);
        c.add(txtName);
        c.add(txtDescription);
        c.add(btnSave);
        c.add(btnCancel);
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
        loadTrainingCodes();
    }