package Account;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

ublic class AddTraining extends JInternalFrame implements ActionListener{
    private JLabel lblName,lblDescription;
    private JTextField txtName,txtDescription;
    private JButton btnSave,btnCancel;
    public AddTraining() {
        super("Add Trainings", false, true, false, true);
        setSize(200, 200);
        setLocation(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        getContentPane().setLayout(new GridLayout(7, 2));

        lblName = new JLabel(" Name");
        lblDescription = new JLabel(" Description");
        txtName = new JTextField();
        txtDescription = new JTextField();
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        c.add(lblName);
        c.add(txtName);
        c.add(lblDescription);
        c.add(txtDescription);
        c.add(btnSave);
        c.add(btnCancel);

        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnSave){
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("jdbc:odbc:user");
                String sql="INSERT INTO Training values(?,?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, txtName.getText());
                ps.setString(2, txtDescription.getText());
                int retval=ps.executeUpdate();
                if(retval>0){
                    JOptionPane.showMessageDialog(null, "Record successfully added to database");
                    txtName.setText("");
                    txtDescription.setText("");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error connection not established or record already exists","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else if(ae.getSource()==btnCancel){
            this.dispose();
        }
    }
}
