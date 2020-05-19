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

public class DeleteTraining extends JInternalFrame implements ActionListener {

    private JLabel lblName,  lblDescription;
    private JTextField txtName,  txtDescription;
    private JButton btnDelete,  btnCancel;

    public DeleteTraining() {
        super("Add Trainings", false, true, false, true);
        setSize(200, 150);
        setLocation(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        getContentPane().setLayout(new GridLayout(5, 2));

        lblName = new JLabel("Name");
        lblDescription = new JLabel("Description");
        txtName = new JTextField();
        txtDescription = new JTextField();
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");

        c.add(lblName);
        c.add(txtName);
        c.add(lblDescription);
        c.add(txtDescription);
        c.add(btnDelete);
        c.add(btnCancel);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        loadSubjeTrainingctCodes();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnDelete) {
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con = DriverManager.getConnection("jdbc:odbc:user");
                String sql = "DELETE FROM Training WHERE Trainingname=?";
                PreparedStatement ps = con.prepareStatement(sql);
                int retval = ps.executeUpdate();
                if (retval > 0) {
                    JOptionPane.showMessageDialog(null, "Record successfully deleted");
                    txtName.setText("");
                    txtDescription.setText("");

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        if (ae.getSource() == btnCancel) {
            this.dispose();
        }

    }

    private void loadTrainingCodes() {
        try {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:student");
            String sql = "SELECT Trainingname FROM Training";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
